package core.solver;

import java.awt.Color;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;

import core.optaplaner.domain.CourseGroupOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.optaplaner.solver.TimeTableConstraintProvider;
import server.models.Course;
import server.models.DateSlot;
import server.models.Degree;
import server.models.Department;
import server.models.Professor;
import server.models.Room;
import server.models.RoomType;

public class TimeTableConstraintProviderTest {

    private static final Department DEPARTMENT = new Department(0, "UFR d'info");
    private static final Room ROOM = new Room(1, "Room1", DEPARTMENT, 25, Set.of(RoomType.TD));
    private static final Room ROOM2 = new Room(2, "Room2", DEPARTMENT, 25, Set.of(RoomType.TD));

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
        Professor turing = new Professor(1, "Turing", "Jean", "jean@u-paris.fr", "mdpmdp");
        Professor curie = new Professor(2, "Curie", "Jeanne", "jeanne@u-paris.fr", "mdpmdp");
        Professor darwin = new Professor(3, "Darwin", "Jeanne", "jeanne@u-paris.fr", "mdpmdp");

        Degree group1 = new Degree(1, "Group1");
        Degree group2 = new Degree(2, "Group2");
        Degree group3 = new Degree(3, "Group3");

        Course subject1 = new Course(0, "Subject1", group1, Color.BLACK);
        Course subject2 = new Course(1, "Subject2", group2, Color.BLACK);
        Course subject3 = new Course(2, "Subject3", group3, Color.BLACK);

        CourseGroupOptaPlaner firstLesson = new CourseGroupOptaPlaner(1, subject1, turing, Duration.ofMinutes(60));
        CourseGroupOptaPlaner conflictingLesson = new CourseGroupOptaPlaner(2, subject2, curie, Duration.ofMinutes(60));
        CourseGroupOptaPlaner nonConflictingLesson = new CourseGroupOptaPlaner(3, subject3, darwin,
                Duration.ofMinutes(60));

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
        Professor turing = new Professor(1, "Turing", "Jean", "jean@u-paris.fr", "mdpmdp");
        Professor curie = new Professor(2, "Curie", "Jeanne", "jeanne@u-paris.fr", "mdpmdp");

        Degree group1 = new Degree(1, "Group1");
        Degree group2 = new Degree(2, "Group2");
        Degree group3 = new Degree(3, "Group3");

        Course subject1 = new Course(0, "Subject1", group1, Color.BLACK);
        Course subject2 = new Course(1, "Subject2", group2, Color.BLACK);
        Course subject3 = new Course(2, "Subject3", group3, Color.BLACK);

        CourseGroupOptaPlaner firstLesson = new CourseGroupOptaPlaner(1, subject1, turing, Duration.ofMinutes(60),
                TIMESLOT1, ROOM);
        CourseGroupOptaPlaner conflictingLesson = new CourseGroupOptaPlaner(2, subject2, turing, Duration.ofMinutes(60),
                TIMESLOT1, ROOM2);
        CourseGroupOptaPlaner nonConflictingLesson = new CourseGroupOptaPlaner(3, subject3, curie,
                Duration.ofMinutes(60), TIMESLOT2, ROOM);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::teacherConflict)
                .given(firstLesson, conflictingLesson, nonConflictingLesson).penalizesBy(1);
    }

    @Test
    void teacherTimeEfficiency() {
        // A teacher prefers to teach sequential lessons and dislikes gaps between
        // lessons.
        Professor turing = new Professor(1, "Turing", "Jean", "jean@u-paris.fr", "mdpmdp");

        Degree group1 = new Degree(1, "Group1");
        Degree group2 = new Degree(2, "Group2");
        Degree group3 = new Degree(3, "Group3");
        Degree group4 = new Degree(4, "Group4");

        Course subject1 = new Course(0, "Subject1", group1, Color.BLACK);
        Course subject2 = new Course(1, "Subject2", group2, Color.BLACK);
        Course subject3 = new Course(2, "Subject3", group3, Color.BLACK);
        Course subject4 = new Course(2, "Subject4", group4, Color.BLACK);

        CourseGroupOptaPlaner firstLesson = new CourseGroupOptaPlaner(1, subject1, turing, Duration.ofMinutes(60),
                TIMESLOT3, ROOM);
        CourseGroupOptaPlaner secondLesson = new CourseGroupOptaPlaner(2, subject2, turing, Duration.ofMinutes(60),
                TIMESLOT4, ROOM);
        CourseGroupOptaPlaner thirdLesson = new CourseGroupOptaPlaner(3, subject3, turing, Duration.ofMinutes(60),
                TIMESLOT5, ROOM);
        CourseGroupOptaPlaner lastLesson = new CourseGroupOptaPlaner(4, subject4, turing, Duration.ofMinutes(60),
                TIMESLOT6, ROOM);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::teacherTimeEfficiency)
                .given(firstLesson, secondLesson, thirdLesson, lastLesson).rewardsWith(4);
    }

}
