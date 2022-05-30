package core.solver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.score.ScoreExplanation;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import core.SolverTimeTable;
import core.optaplaner.SolverOptaplaner;
import core.optaplaner.domain.CourseGroupOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.output.TimeTable;
import server.models.Course;
import server.models.CourseGroup;
import server.models.DateSlot;
import server.models.Degree;
import server.models.Department;
import server.models.Major;
import server.models.MajorCourse;
import server.models.Professor;
import server.models.Room;
import server.models.RoomType;

class SolverOptaplanerTest {

    private static final Duration H2 = Duration.ofMinutes(115);
    private static final Duration H1 = Duration.ofMinutes(55);
    private static final String BLACK = "000000";

    private TimeTableOptaPlaner problem;
    private SolverTimeTable solverTimeTable;

    @BeforeEach
    void before() {
        generateDemoData();
        solverTimeTable = new SolverOptaplaner();
    }

    public void generateDemoData() {
        List<DateSlot> timeslotList = new ArrayList<>(10);
        timeslotList.add(new DateSlot(DayOfWeek.MONDAY, LocalTime.of(8, 30)));
        timeslotList.add(new DateSlot(DayOfWeek.MONDAY, LocalTime.of(9, 30)));
        timeslotList.add(new DateSlot(DayOfWeek.MONDAY, LocalTime.of(10, 30)));
        timeslotList.add(new DateSlot(DayOfWeek.MONDAY, LocalTime.of(13, 30)));
        timeslotList.add(new DateSlot(DayOfWeek.MONDAY, LocalTime.of(14, 30)));

        timeslotList.add(new DateSlot(DayOfWeek.TUESDAY, LocalTime.of(8, 30)));
        timeslotList.add(new DateSlot(DayOfWeek.TUESDAY, LocalTime.of(9, 30)));
        timeslotList.add(new DateSlot(DayOfWeek.TUESDAY, LocalTime.of(10, 30)));
        timeslotList.add(new DateSlot(DayOfWeek.TUESDAY, LocalTime.of(13, 30)));
        timeslotList.add(new DateSlot(DayOfWeek.TUESDAY, LocalTime.of(14, 30)));

        Department ufrInfo = new Department("UFR d'informatique");

        List<Room> roomList = new ArrayList<>(3);
        roomList.add(new Room(1, "Room A", ufrInfo, 3, Set.of(RoomType.TD)));
        roomList.add(new Room(2, "Room B", ufrInfo, 3, Set.of(RoomType.TD)));
        roomList.add(new Room(3, "Room C", ufrInfo, 3, Set.of(RoomType.TD)));

        List<CourseGroupOptaPlaner> lessonList = new ArrayList<>();
        long id = 0;

        Professor turing = new Professor(1, "Turing", "Jean", "jean@u-paris.fr", "mdpmdp");
        Professor curie = new Professor(2, "Curie", "Jeanne", "jeanne@u-paris.fr", "mdpmdp");
        Professor darwin = new Professor(3, "Darwin", "Jeanne", "jeanne@u-paris.fr", "mdpmdp");
        Professor jones = new Professor(4, "Jones", "Jeanne", "jeanne@u-paris.fr", "mdpmdp");
        Professor cruz = new Professor(5, "Cruz", "Jeanne", "jeanne@u-paris.fr", "mdpmdp");

        Degree grade9 = new Degree("9th grade");
        Degree grade10 = new Degree("10th grade");

        Major impairs = new Major("Impairs");
        Major genial = new Major("Genial");
        Major data = new Major("Data");

        Course math9 = new Course(1, "Math", grade9, BLACK);
        Course physics9 = new Course(2, "Physics", grade9, BLACK);
        Course chemistry9 = new Course(3, "Chemistry", grade9, BLACK);
        Course biology9 = new Course(4, "Biology", grade9, BLACK);
        Course history9 = new Course(5, "History", grade9, BLACK);
        Course english9 = new Course(6, "English", grade9, BLACK);
        Course spanish9 = new Course(7, "Spanish", grade9, BLACK);
        Course math10 = new Course(8, "Math", grade10, BLACK);
        Course physics10 = new Course(9, "Physics", grade10, BLACK);
        Course chemistry10 = new Course(10, "Chemistry", grade10, BLACK);
        Course history10 = new Course(11, "History", grade10, BLACK);
        Course english10 = new Course(12, "English", grade10, BLACK);
        Course spanish10 = new Course(13, "Spanish", grade10, BLACK);
        Course french10 = new Course(14, "French", grade10, BLACK);
        Course geography10 = new Course(15, "Geography", grade10, BLACK);

        CourseGroup courseGroup1 = new CourseGroup(1, new MajorCourse(math9, genial), H1, 20, RoomType.CM);
        CourseGroup courseGroup2 = new CourseGroup(2, new MajorCourse(math9, impairs), H1, 20, RoomType.CM);
        CourseGroup courseGroup3 = new CourseGroup(1, new MajorCourse(physics9, genial), H1, 20, RoomType.CM);
        CourseGroup courseGroup4 = new CourseGroup(1, new MajorCourse(chemistry9, impairs), H1, 20, RoomType.TD);
        CourseGroup courseGroup5 = new CourseGroup(1, new MajorCourse(biology9, genial), H1, 20, RoomType.CM);
        CourseGroup courseGroup6 = new CourseGroup(1, new MajorCourse(history9, genial), H1, 20, RoomType.CM);
        CourseGroup courseGroup7 = new CourseGroup(1, new MajorCourse(english9, impairs), H1, 20, RoomType.CM);
        CourseGroup courseGroup8 = new CourseGroup(2, new MajorCourse(english9, genial), H2, 20, RoomType.TD);
        CourseGroup courseGroup9 = new CourseGroup(1, new MajorCourse(spanish9, genial), H2, 20, RoomType.CM);
        CourseGroup courseGroup10 = new CourseGroup(2, new MajorCourse(spanish9, impairs), H2, 20, RoomType.TP);
        CourseGroup courseGroup11 = new CourseGroup(1, new MajorCourse(math10, impairs), H2, 20, RoomType.TP);
        CourseGroup courseGroup12 = new CourseGroup(2, new MajorCourse(math10, genial), H1, 20, RoomType.TD);
        CourseGroup courseGroup13 = new CourseGroup(3, new MajorCourse(math10, data), H1, 20, RoomType.CM);
        CourseGroup courseGroup14 = new CourseGroup(1, new MajorCourse(physics10, genial), H1, 20, RoomType.CM);
        CourseGroup courseGroup15 = new CourseGroup(1, new MajorCourse(chemistry10, impairs), H1, 20, RoomType.TD);
        CourseGroup courseGroup16 = new CourseGroup(1, new MajorCourse(french10, impairs), H1, 20, RoomType.CM);
        CourseGroup courseGroup17 = new CourseGroup(1, new MajorCourse(geography10, genial), H1, 20, RoomType.CM);
        CourseGroup courseGroup18 = new CourseGroup(1, new MajorCourse(history10, impairs), H1, 20, RoomType.TD);
        CourseGroup courseGroup19 = new CourseGroup(1, new MajorCourse(english10, genial), H1, 20, RoomType.CM);
        CourseGroup courseGroup20 = new CourseGroup(1, new MajorCourse(spanish10, genial), H1, 20, RoomType.TP);

        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup1, turing));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup2, turing));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup3, curie));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup4, curie));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup5, darwin));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup6, jones));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup7, jones));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup8, jones));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup9, cruz));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup10, cruz));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup11, turing));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup12, turing));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup13, turing));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup14, curie));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup15, curie));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup16, curie));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup17, darwin));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup18, jones));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup19, cruz));
        lessonList.add(new CourseGroupOptaPlaner(id++, courseGroup20, cruz));

        problem = new TimeTableOptaPlaner(timeslotList, roomList, lessonList);
    }

    @Disabled
    @Test
    void solveTest() throws IOException {
        TimeTable solution = solverTimeTable.solve(problem.toOutput(), List.of(), List.of());

        assertEquals(IOUtils.toString(SolverOptaplanerTest.class.getResourceAsStream("/res.txt"), "UTF-8"),
                solution.toString());
    }

    @Disabled
    @Test
    void verifyTest() throws IOException {
        ScoreExplanation<TimeTableOptaPlaner, HardSoftScore> verify = solverTimeTable
                .verify(solverTimeTable.solve(problem.toOutput(), List.of(), List.of()), List.of(), List.of());

        assertEquals(
                """
                        Explanation of score (0hard/0soft):
                            Constraint match totals:
                                -1soft: constraint (Professor room stability) has 1 matches:
                                    -1soft: justifications ([Biology(4), Geography(16)])
                                -1soft: constraint (Professor Turing does not want to teach Math in room C at 8H30) has 1 matches:
                                    -1soft: justifications ([Math(11)])
                                -1soft: constraint (Professor M. Curie does not want to teach French in Room A) has 1 matches:
                                    -1soft: justifications ([French(15)])
                                3soft: constraint (Professor time efficiency) has 3 matches:
                                    1soft: justifications ([Spanish(9), Spanish(8)])
                                    1soft: justifications ([Math(11), Math(1)])
                                    ...
                            Indictments (top 5 of 9):
                                -1soft: justification (Biology(4)) has 1 matches:
                                    -1soft: constraint (Professor room stability)
                                -1soft: justification (Geography(16)) has 1 matches:
                                    -1soft: constraint (Professor room stability)
                                -1soft: justification (French(15)) has 1 matches:
                                    -1soft: constraint (Professor M. Curie does not want to teach French in Room A)
                                0: justification (Math(11)) has 2 matches:
                                    -1soft: constraint (Professor Turing does not want to teach Math in room C at 8H30)
                                    1soft: constraint (Professor time efficiency)
                                1soft: justification (Spanish(9)) has 1 matches:
                                    1soft: constraint (Professor time efficiency)
                                ...
                        """,
                verify.toString());
    }

}
