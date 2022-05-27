package core.solver;

import core.optaplaner.domain.CourseGroupOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.optaplaner.solver.TimeTableConstraintProvider;
import org.junit.jupiter.api.Test;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;
import server.models.*;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

class TimeTableConstraintProviderTest {

    private static final String BLACK = "000000";

    private static final Department DEPARTMENT = new Department("UFR d'info");
    private static final Room ROOM = new Room("Room1", DEPARTMENT, 25, Set.of(RoomType.TD));
    private static final Room ROOM2 = new Room("Room2", DEPARTMENT, 25, Set.of(RoomType.TD));

    private static final DateSlot TIMESLOT1 = new DateSlot(DayOfWeek.MONDAY, LocalTime.of(9, 0));
    private static final DateSlot TIMESLOT2 = new DateSlot(DayOfWeek.TUESDAY, LocalTime.of(9, 0));

    private static final DateSlot TIMESLOT3 = new DateSlot(DayOfWeek.MONDAY, LocalTime.of(7, 0));
    private static final DateSlot TIMESLOT4 = new DateSlot(DayOfWeek.MONDAY, LocalTime.of(8, 0));
    private static final DateSlot TIMESLOT5 = new DateSlot(DayOfWeek.MONDAY, LocalTime.of(9, 0));
    private static final DateSlot TIMESLOT6 = new DateSlot(DayOfWeek.MONDAY, LocalTime.of(10, 0));

    ConstraintVerifier<TimeTableConstraintProvider, TimeTableOptaPlaner> constraintVerifier = ConstraintVerifier
            .build(new TimeTableConstraintProvider(), TimeTableOptaPlaner.class, CourseGroupOptaPlaner.class);

    @Test
    void roomConflict() {
        Professor turing = new Professor("Turing", "Jean", "jean@u-paris.fr", "mdpmdp");
        Professor curie = new Professor("Curie", "Jeanne", "jeanne@u-paris.fr", "mdpmdp");
        Professor darwin = new Professor("Darwin", "Jeanne", "jeanne@u-paris.fr", "mdpmdp");

        Degree group1 = new Degree("Group1");
        Degree group2 = new Degree("Group2");
        Degree group3 = new Degree("Group3");

        Course subject1 = new Course("Subject1", group1, BLACK);
        Course subject2 = new Course("Subject2", group2, BLACK);
        Course subject3 = new Course("Subject3", group3, BLACK);

        Major impairs = new Major("Impairs");
        Major genial = new Major("Genial");

        CourseGroup courseGroup1 = new CourseGroup(1, new MajorCourse(subject1, impairs), Duration.ofMinutes(60), 0, RoomType.CM);
        CourseGroup courseGroup2 = new CourseGroup(2, new MajorCourse(subject2, genial), Duration.ofMinutes(60), 0, RoomType.TD);
        CourseGroup courseGroup3 = new CourseGroup(3, new MajorCourse(subject3, impairs), Duration.ofMinutes(60), 0, RoomType.CM);

        CourseGroupOptaPlaner firstLesson = new CourseGroupOptaPlaner(1, courseGroup1, turing);
        CourseGroupOptaPlaner conflictingLesson = new CourseGroupOptaPlaner(2, courseGroup2, curie);
        CourseGroupOptaPlaner nonConflictingLesson = new CourseGroupOptaPlaner(3, courseGroup3, darwin);

        firstLesson.setRoom(ROOM);
        firstLesson.setDateSlot(TIMESLOT1);

        conflictingLesson.setRoom(ROOM);
        conflictingLesson.setDateSlot(TIMESLOT1);

        nonConflictingLesson.setRoom(ROOM);
        nonConflictingLesson.setDateSlot(TIMESLOT2);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::roomConflict)
                .given(firstLesson, conflictingLesson, nonConflictingLesson).penalizesBy(1);
    }

    @Test
    void teacherConflict() {
        Professor turing = new Professor("Turing", "Jean", "jean@u-paris.fr", "mdpmdp");
        Professor curie = new Professor("Curie", "Jeanne", "jeanne@u-paris.fr", "mdpmdp");

        Degree group1 = new Degree("Group1");
        Degree group2 = new Degree("Group2");
        Degree group3 = new Degree("Group3");

        Course subject1 = new Course("Subject1", group1, BLACK);
        Course subject2 = new Course("Subject2", group2, BLACK);
        Course subject3 = new Course("Subject3", group3, BLACK);

        Major impairs = new Major("Impairs");
        Major genial = new Major("Genial");

        CourseGroup courseGroup1 = new CourseGroup(1, new MajorCourse(subject1, impairs), Duration.ofMinutes(60), 0, RoomType.CM);
        CourseGroup courseGroup2 = new CourseGroup(2, new MajorCourse(subject2, genial), Duration.ofMinutes(60), 0, RoomType.TD);
        CourseGroup courseGroup3 = new CourseGroup(3, new MajorCourse(subject3, impairs), Duration.ofMinutes(60), 0, RoomType.CM);

        CourseGroupOptaPlaner firstLesson = new CourseGroupOptaPlaner(1, courseGroup1, turing, TIMESLOT1, ROOM);
        CourseGroupOptaPlaner conflictingLesson = new CourseGroupOptaPlaner(2, courseGroup2, turing, TIMESLOT1, ROOM2);
        CourseGroupOptaPlaner nonConflictingLesson = new CourseGroupOptaPlaner(3, courseGroup3, curie, TIMESLOT2, ROOM);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::teacherConflict)
                .given(firstLesson, conflictingLesson, nonConflictingLesson).penalizesBy(1);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::teacherConflict)
                .given(firstLesson, nonConflictingLesson).penalizesBy(0);
    }

    @Test
    void teacherTimeEfficiency() {
        // A teacher prefers to teach sequential lessons and dislikes gaps between
        // lessons.
        Professor turing = new Professor("Turing", "Jean", "jean@u-paris.fr", "mdpmdp");

        Degree group1 = new Degree("Group1");
        Degree group2 = new Degree("Group2");
        Degree group3 = new Degree("Group3");
        Degree group4 = new Degree("Group4");

        Course subject1 = new Course("Subject1", group1, BLACK);
        Course subject2 = new Course("Subject2", group2, BLACK);
        Course subject3 = new Course("Subject3", group3, BLACK);
        Course subject4 = new Course("Subject4", group4, BLACK);

        Major impairs = new Major("Impairs");
        Major genial = new Major("Genial");

        CourseGroup courseGroup1 = new CourseGroup(1, new MajorCourse(subject1, impairs), Duration.ofMinutes(60), 0, RoomType.CM);
        CourseGroup courseGroup2 = new CourseGroup(2, new MajorCourse(subject2, genial), Duration.ofMinutes(60), 0, RoomType.TD);
        CourseGroup courseGroup3 = new CourseGroup(3, new MajorCourse(subject3, impairs), Duration.ofMinutes(60), 0, RoomType.CM);
        CourseGroup courseGroup4 = new CourseGroup(4, new MajorCourse(subject4, genial), Duration.ofMinutes(60), 0, RoomType.TP);

        CourseGroupOptaPlaner firstLesson = new CourseGroupOptaPlaner(1, courseGroup1, turing, TIMESLOT3, ROOM);
        CourseGroupOptaPlaner secondLesson = new CourseGroupOptaPlaner(2, courseGroup2, turing, TIMESLOT4, ROOM);
        CourseGroupOptaPlaner thirdLesson = new CourseGroupOptaPlaner(3, courseGroup3, turing, TIMESLOT5, ROOM);
        CourseGroupOptaPlaner lastLesson = new CourseGroupOptaPlaner(4, courseGroup4, turing, TIMESLOT6, ROOM);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::teacherTimeEfficiency)
                .given(firstLesson, secondLesson, thirdLesson, lastLesson).rewardsWith(3);
    }

    @Test
    void techLessonBefore() {
        Professor turing = new Professor("Turing", "Jean", "jean@u-paris.fr", "mdpmdp");

        Degree group1 = new Degree("Group1");
        Degree group2 = new Degree("Group2");
        Degree group3 = new Degree("Group3");
        Degree group4 = new Degree("Group4");

        Course subject1 = new Course("Subject1", group1, BLACK);
        Course subject2 = new Course("Subject2", group2, BLACK);
        Course subject3 = new Course("Subject3", group3, BLACK);
        Course subject4 = new Course("Subject4", group4, BLACK);

        Major impairs = new Major("Impairs");
        Major genial = new Major("Genial");

        CourseGroup courseGroup1 = new CourseGroup(1, new MajorCourse(subject1, impairs), Duration.ofMinutes(60), 0, RoomType.CM);
        CourseGroup courseGroup2 = new CourseGroup(2, new MajorCourse(subject2, genial), Duration.ofMinutes(60), 0, RoomType.TD);
        CourseGroup courseGroup3 = new CourseGroup(3, new MajorCourse(subject3, impairs), Duration.ofMinutes(60), 0, RoomType.CM);
        CourseGroup courseGroup4 = new CourseGroup(4, new MajorCourse(subject4, genial), Duration.ofMinutes(60), 0, RoomType.TP);

        CourseGroupOptaPlaner firstLesson = new CourseGroupOptaPlaner(1, courseGroup1, turing, TIMESLOT4, ROOM);
        CourseGroupOptaPlaner secondLesson = new CourseGroupOptaPlaner(2, courseGroup2, turing, TIMESLOT3, ROOM);

        CourseGroupOptaPlaner thirdLesson = new CourseGroupOptaPlaner(3, courseGroup3, turing, TIMESLOT5, ROOM);
        CourseGroupOptaPlaner lastLesson = new CourseGroupOptaPlaner(4, courseGroup4, turing, TIMESLOT6, ROOM);

        constraintVerifier.verifyThat((arg, arg2) ->TimeTableConstraintProvider.techLessonBefore(arg2, subject1.getName(), subject2.getName()))
                .given(firstLesson, secondLesson).penalizesBy(1);

        constraintVerifier.verifyThat((arg1, arg2) -> TimeTableConstraintProvider.techLessonBefore(arg2, subject3.getName(), subject4.getName()))
                .given(thirdLesson, lastLesson).penalizesBy(0);
    }
}
