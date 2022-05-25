package core.solver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.score.ScoreExplanation;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import core.SolverTimeTable;
import core.optaplaner.SolverOptaplaner;
import core.optaplaner.domain.CourseGroupOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.output.TimeTable;
import server.models.Course;
import server.models.DateSlot;
import server.models.Degree;
import server.models.Department;
import server.models.Professor;
import server.models.Room;
import server.models.RoomType;

public class SolverOptaplanerTest {

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

        Department ufrInfo = new Department(1, "UFR d'informatique");

        List<Room> roomList = new ArrayList<>(3);
        roomList.add(new Room(0, "Room A", ufrInfo, 3, Set.of(RoomType.TD)));
        roomList.add(new Room(1, "Room B", ufrInfo, 3, Set.of(RoomType.TD)));
        roomList.add(new Room(2, "Room C", ufrInfo, 3, Set.of(RoomType.TD)));

        List<CourseGroupOptaPlaner> lessonList = new ArrayList<>();
        long id = 0;

        Professor turing = new Professor(0, "Turing", "Jean", "jean@u-paris.fr","mdpmdp");
        Professor curie = new Professor(1, "Curie", "Jeanne", "jeanne@u-paris.fr","mdpmdp");
        Professor darwin = new Professor(2, "Darwin", "Jeanne", "jeanne@u-paris.fr","mdpmdp");
        Professor jones = new Professor(3, "Jones", "Jeanne", "jeanne@u-paris.fr","mdpmdp");
        Professor cruz = new Professor(4, "Cruz", "Jeanne", "jeanne@u-paris.fr","mdpmdp");

        Degree grade9 = new Degree(0, "9th grade");
        Degree grade10 = new Degree(1, "10th grade");

        Course math9 = new Course(0, "Math", grade9, "000000");
        Course physics9 = new Course(1, "Physics", grade9, "000000");
        Course chemistry9 = new Course(2, "Chemistry", grade9, "000000");
        Course biology9 = new Course(3, "Biology", grade9, "000000");
        Course history9 = new Course(4, "History", grade9, "000000");
        Course english9 = new Course(5, "English", grade9, "000000");
        Course spanish9 = new Course(6, "Spanish", grade9, "000000");
        Course math10 = new Course(7, "Math", grade10, "000000");
        Course physics10 = new Course(8, "Physics", grade10, "000000");
        Course chemistry10 = new Course(9, "Chemistry", grade10, "000000");
        Course history10 = new Course(10, "History", grade10, "000000");
        Course english10 = new Course(11, "English", grade10, "000000");
        Course spanish10 = new Course(12, "Spanish", grade10, "000000");
        Course french10 = new Course(13, "French", grade10, "000000");
        Course geography10 = new Course(14, "Geography", grade10, "000000");

        lessonList.add(new CourseGroupOptaPlaner(id++, math9, turing, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, math9, turing, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, physics9, curie, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, chemistry9, curie, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, biology9, darwin, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, history9, jones, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, english9, jones, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, english9, jones, Duration.ofMinutes(120)));
        lessonList.add(new CourseGroupOptaPlaner(id++, spanish9, cruz, Duration.ofMinutes(120)));
        lessonList.add(new CourseGroupOptaPlaner(id++, spanish9, cruz, Duration.ofMinutes(120)));
        lessonList.add(new CourseGroupOptaPlaner(id++, math10, turing, Duration.ofMinutes(120)));
        lessonList.add(new CourseGroupOptaPlaner(id++, math10, turing, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, math10, turing, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, physics10, curie, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, chemistry10, curie, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, french10, curie, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, geography10, darwin, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, history10, jones, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, english10, cruz, Duration.ofMinutes(60)));
        lessonList.add(new CourseGroupOptaPlaner(id++, spanish10, cruz, Duration.ofMinutes(60)));

        problem = new TimeTableOptaPlaner(timeslotList, roomList, lessonList);
    }

    @Test
    void solveTest() throws IOException {
        TimeTable solution = solverTimeTable.solve(problem.toOutput(), List.of(), List.of());

        assertEquals(IOUtils.toString(SolverOptaplanerTest.class.getResourceAsStream("/res.txt"), "UTF-8"),
                solution.toString());
    }

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
