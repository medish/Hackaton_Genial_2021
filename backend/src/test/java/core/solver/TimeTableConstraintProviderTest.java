package core.solver;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;

import core.optaplaner.domain.LessonOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.optaplaner.solver.TimeTableConstraintProvider;
import core.output.Timeslot;
import server.models.Department;
import server.models.Professor;
import server.models.Room;
import server.models.RoomId;
import server.models.RoomType;

public class TimeTableConstraintProviderTest {
    private static final Room ROOM = new Room(new RoomId("Room1", new Department("Info", "UFR d'info")), 25,
            new RoomType("TD"));
    private static final Room ROOM2 = new Room(new RoomId("Room2", new Department("Info", "UFR d'info")), 25,
            new RoomType("TD"));

    private static final Timeslot TIMESLOT1 = new Timeslot(DayOfWeek.MONDAY, LocalTime.of(9, 0));
    private static final Timeslot TIMESLOT2 = new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(9, 0));

    private static final Timeslot TIMESLOT3 = new Timeslot(DayOfWeek.MONDAY, LocalTime.of(7, 0));
    private static final Timeslot TIMESLOT4 = new Timeslot(DayOfWeek.MONDAY, LocalTime.of(8, 0));
    private static final Timeslot TIMESLOT5 = new Timeslot(DayOfWeek.MONDAY, LocalTime.of(9, 0));
    private static final Timeslot TIMESLOT6 = new Timeslot(DayOfWeek.MONDAY, LocalTime.of(10, 0));

    ConstraintVerifier<TimeTableConstraintProvider, TimeTableOptaPlaner> constraintVerifier = ConstraintVerifier
            .build(new TimeTableConstraintProvider(), TimeTableOptaPlaner.class, LessonOptaPlaner.class);

    @Test
    void roomConflict() {

        Professor turing = new Professor("1", "Turing", "Jean", "jean@u-paris.fr", false);
        Professor curie = new Professor("2", "Curie", "Jeanne", "jeanne@u-paris.fr", false);
        Professor darwin = new Professor("3", "Darwin", "Jeanne", "jeanne@u-paris.fr", false);

        LessonOptaPlaner firstLesson = new LessonOptaPlaner(1, "Subject1", turing, "Group1", Duration.ofMinutes(60));
        LessonOptaPlaner conflictingLesson = new LessonOptaPlaner(2, "Subject2", curie, "Group2",
                Duration.ofMinutes(60));
        LessonOptaPlaner nonConflictingLesson = new LessonOptaPlaner(3, "Subject3", darwin, "Group3",
                Duration.ofMinutes(60));

        firstLesson.setRoom(ROOM);
        firstLesson.setTimeslot(TIMESLOT1);

        conflictingLesson.setRoom(ROOM);
        conflictingLesson.setTimeslot(TIMESLOT1);

        nonConflictingLesson.setRoom(ROOM);
        nonConflictingLesson.setTimeslot(TIMESLOT2);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::roomConflict)
                .given(firstLesson, conflictingLesson, nonConflictingLesson).penalizesBy(1);
    }

    @Test
    void teacherConflict() {
        Professor turing = new Professor("1", "Turing", "Jean", "jean@u-paris.fr", false);
        Professor curie = new Professor("2", "Curie", "Jeanne", "jeanne@u-paris.fr", false);

        LessonOptaPlaner firstLesson = new LessonOptaPlaner(1, "Subject1", turing, "Group1", TIMESLOT1,
                Duration.ofMinutes(60), ROOM);
        LessonOptaPlaner conflictingLesson = new LessonOptaPlaner(2, "Subject2", turing, "Group2", TIMESLOT1,
                Duration.ofMinutes(60), ROOM2);

        LessonOptaPlaner nonConflictingLesson = new LessonOptaPlaner(3, "Subject3", curie, "Group3", TIMESLOT2,
                Duration.ofMinutes(60), ROOM);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::teacherConflict)
                .given(firstLesson, conflictingLesson, nonConflictingLesson).penalizesBy(1);
    }

    @Test
    void teacherTimeEfficiency() {
        // A teacher prefers to teach sequential lessons and dislikes gaps between
        // lessons.
        Professor turing = new Professor("1", "Turing", "Jean", "jean@u-paris.fr", false);

        LessonOptaPlaner firstLesson = new LessonOptaPlaner(1, "Subject1", turing, "Group1", TIMESLOT3,
                Duration.ofMinutes(60), ROOM);
        LessonOptaPlaner secondLesson = new LessonOptaPlaner(2, "Subject2", turing, "Group2", TIMESLOT4,
                Duration.ofMinutes(60), ROOM);
        LessonOptaPlaner thirdLesson = new LessonOptaPlaner(3, "Subject3", turing, "Group3", TIMESLOT5,
                Duration.ofMinutes(60), ROOM);
        LessonOptaPlaner lastLesson = new LessonOptaPlaner(4, "Subject4", turing, "Group4", TIMESLOT6,
                Duration.ofMinutes(60), ROOM);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::teacherTimeEfficiency)
                .given(firstLesson, secondLesson, thirdLesson, lastLesson).rewardsWith(4);
    }

}
