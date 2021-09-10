package core.solver;

import java.awt.Color;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;

import core.optaplaner.domain.LessonOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.optaplaner.solver.TimeTableConstraintProvider;
import server.models.Course;
import server.models.Date;
import server.models.DateId;
import server.models.Degree;
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

    private static final Date TIMESLOT1 = new Date(new DateId(DayOfWeek.MONDAY, LocalTime.of(9, 0)));
    private static final Date TIMESLOT2 = new Date(new DateId(DayOfWeek.TUESDAY, LocalTime.of(9, 0)));

    private static final Date TIMESLOT3 = new Date(new DateId(DayOfWeek.MONDAY, LocalTime.of(7, 0)));
    private static final Date TIMESLOT4 = new Date(new DateId(DayOfWeek.MONDAY, LocalTime.of(8, 0)));
    private static final Date TIMESLOT5 = new Date(new DateId(DayOfWeek.MONDAY, LocalTime.of(9, 0)));
    private static final Date TIMESLOT6 = new Date(new DateId(DayOfWeek.MONDAY, LocalTime.of(10, 0)));

    ConstraintVerifier<TimeTableConstraintProvider, TimeTableOptaPlaner> constraintVerifier = ConstraintVerifier
            .build(new TimeTableConstraintProvider(), TimeTableOptaPlaner.class, LessonOptaPlaner.class);

    @Test
    void roomConflict() {
        Professor turing = new Professor("1", "Turing", "Jean", "jean@u-paris.fr", false);
        Professor curie = new Professor("2", "Curie", "Jeanne", "jeanne@u-paris.fr", false);
        Professor darwin = new Professor("3", "Darwin", "Jeanne", "jeanne@u-paris.fr", false);

        Degree group1 = new Degree("Group1", "Group1");
        Degree group2 = new Degree("Group2", "Group2");
        Degree group3 = new Degree("Group3", "Group3");

        Course subject1 = new Course(0, "Subject1", Set.of(group1), Color.BLACK);
        Course subject2 = new Course(1, "Subject2", Set.of(group2), Color.BLACK);
        Course subject3 = new Course(2, "Subject3", Set.of(group3), Color.BLACK);

        LessonOptaPlaner firstLesson = new LessonOptaPlaner(1, subject1, turing, Duration.ofMinutes(60));
        LessonOptaPlaner conflictingLesson = new LessonOptaPlaner(2, subject2, curie, Duration.ofMinutes(60));
        LessonOptaPlaner nonConflictingLesson = new LessonOptaPlaner(3, subject3, darwin, Duration.ofMinutes(60));

        firstLesson.setRoom(ROOM);
        firstLesson.setDate(TIMESLOT1);

        conflictingLesson.setRoom(ROOM);
        conflictingLesson.setDate(TIMESLOT1);

        nonConflictingLesson.setRoom(ROOM);
        nonConflictingLesson.setDate(TIMESLOT2);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::roomConflict)
                .given(firstLesson, conflictingLesson, nonConflictingLesson).penalizesBy(1);
    }

    @Test
    void teacherConflict() {
        Professor turing = new Professor("1", "Turing", "Jean", "jean@u-paris.fr", false);
        Professor curie = new Professor("2", "Curie", "Jeanne", "jeanne@u-paris.fr", false);

        Degree group1 = new Degree("Group1", "Group1");
        Degree group2 = new Degree("Group2", "Group2");
        Degree group3 = new Degree("Group3", "Group3");

        Course subject1 = new Course(0, "Subject1", Set.of(group1), Color.BLACK);
        Course subject2 = new Course(1, "Subject2", Set.of(group2), Color.BLACK);
        Course subject3 = new Course(2, "Subject3", Set.of(group3), Color.BLACK);

        LessonOptaPlaner firstLesson = new LessonOptaPlaner(1, subject1, turing, Duration.ofMinutes(60), TIMESLOT1,
                ROOM);
        LessonOptaPlaner conflictingLesson = new LessonOptaPlaner(2, subject2, turing, Duration.ofMinutes(60), TIMESLOT1,
                ROOM2);
        LessonOptaPlaner nonConflictingLesson = new LessonOptaPlaner(3, subject3, curie, Duration.ofMinutes(60),
                TIMESLOT2, ROOM);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::teacherConflict)
                .given(firstLesson, conflictingLesson, nonConflictingLesson).penalizesBy(1);
    }

    @Test
    void teacherTimeEfficiency() {
        // A teacher prefers to teach sequential lessons and dislikes gaps between
        // lessons.
        Professor turing = new Professor("1", "Turing", "Jean", "jean@u-paris.fr", false);

        Degree group1 = new Degree("Group1", "Group1");
        Degree group2 = new Degree("Group2", "Group2");
        Degree group3 = new Degree("Group3", "Group3");
        Degree group4 = new Degree("Group4", "Group4");

        Course subject1 = new Course(0, "Subject1", Set.of(group1), Color.BLACK);
        Course subject2 = new Course(1, "Subject2", Set.of(group2), Color.BLACK);
        Course subject3 = new Course(2, "Subject3", Set.of(group3), Color.BLACK);
        Course subject4 = new Course(2, "Subject4", Set.of(group4), Color.BLACK);

        LessonOptaPlaner firstLesson = new LessonOptaPlaner(1, subject1, turing, Duration.ofMinutes(60), TIMESLOT3,
                ROOM);
        LessonOptaPlaner secondLesson = new LessonOptaPlaner(2, subject2, turing, Duration.ofMinutes(60), TIMESLOT4,
                ROOM);
        LessonOptaPlaner thirdLesson = new LessonOptaPlaner(3, subject3, turing, Duration.ofMinutes(60), TIMESLOT5,
                ROOM);
        LessonOptaPlaner lastLesson = new LessonOptaPlaner(4, subject4, turing, Duration.ofMinutes(60), TIMESLOT6,
                ROOM);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::teacherTimeEfficiency)
                .given(firstLesson, secondLesson, thirdLesson, lastLesson).rewardsWith(4);
    }

}
