package core.solver;

import core.optaplaner.domain.LessonOptaPlaner;
import core.optaplaner.solver.TimeTableConstraintProvider;
import core.output.TimeTable;
import core.output.Timeslot;
import org.junit.jupiter.api.Test;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;
import server.models.Professor;
import server.models.Room;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimeTableConstraintProviderTest {
    private static final Room ROOM = new Room("Room1");
    private static final Room ROOM2 = new Room("Room2");

    private static final Timeslot TIMESLOT1 = new Timeslot(DayOfWeek.MONDAY, LocalTime.of(9,0), LocalTime.NOON);
    private static final Timeslot TIMESLOT2 = new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(9,0), LocalTime.NOON);

    ConstraintVerifier<TimeTableConstraintProvider, TimeTable> constraintVerifier = ConstraintVerifier.build(
            new TimeTableConstraintProvider(), TimeTable.class, LessonOptaPlaner.class);
    @Test
    void roomConflict() {

        Professor turing = new Professor("1", "Turing", "Jean", "jean@u-paris.fr", false);
        Professor curie = new Professor("2", "Curie", "Jeanne", "jeanne@u-paris.fr", false);
        Professor darwin = new Professor("3", "Darwin", "Jeanne", "jeanne@u-paris.fr", false);


        LessonOptaPlaner firstLesson = new LessonOptaPlaner(1, "Subject1", turing, "Group1");
        LessonOptaPlaner conflictingLesson = new LessonOptaPlaner(2, "Subject2", curie, "Group2");
        LessonOptaPlaner nonConflictingLesson = new LessonOptaPlaner(3, "Subject3", darwin, "Group3");

        firstLesson.setRoom(ROOM);
        firstLesson.setTimeslot(TIMESLOT1);

        conflictingLesson.setRoom(ROOM);
        conflictingLesson.setTimeslot(TIMESLOT1);

        nonConflictingLesson.setRoom(ROOM);
        nonConflictingLesson.setTimeslot(TIMESLOT2);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::roomConflict)
                .given(firstLesson, conflictingLesson, nonConflictingLesson)
                .penalizesBy(1);
    }

    @Test
    void teacherConflict() {
        Professor turing = new Professor("1", "Turing", "Jean", "jean@u-paris.fr", false);
        Professor curie = new Professor("2", "Curie", "Jeanne", "jeanne@u-paris.fr", false);

        LessonOptaPlaner firstLesson = new LessonOptaPlaner(1, "Subject1", turing, "Group1", TIMESLOT1, ROOM);
        LessonOptaPlaner conflictingLesson = new LessonOptaPlaner(2, "Subject2", turing, "Group2", TIMESLOT1, ROOM2);

        LessonOptaPlaner nonConflictingLesson = new LessonOptaPlaner(3, "Subject3", curie, "Group3", TIMESLOT2, ROOM);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::teacherConflict)
                .given(firstLesson, conflictingLesson, nonConflictingLesson)
                .penalizesBy(1);
    }

    @Test
    void teacherTimeEfficiency() {
        //A teacher prefers to teach sequential lessons and dislikes gaps between
        // lessons.
        Professor turing = new Professor("1", "Turing", "Jean", "jean@u-paris.fr", false);
        LessonOptaPlaner firstLesson = new LessonOptaPlaner(1, "Subject1", turing, "Group1", TIMESLOT1, ROOM);
        LessonOptaPlaner secondLesson = new LessonOptaPlaner(2, "Subject2", turing, "Group1", TIMESLOT1, ROOM);
        LessonOptaPlaner thirdLesson = new LessonOptaPlaner(3, "Subject3", turing, "Group1", TIMESLOT1, ROOM);
        LessonOptaPlaner lastLesson = new LessonOptaPlaner(4, "Subject4", turing, "Group1", TIMESLOT1, ROOM);

        /*constraintVerifier.verifyThat(TimeTableConstraintProvider::teacherTimeEfficiency)
                .given(firstLesson, secondLesson, thirdLesson, lastLesson)
                .rewardsWith(1);*/
    }





}
