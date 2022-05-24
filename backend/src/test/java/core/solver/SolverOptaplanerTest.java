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
import core.optaplaner.domain.LessonOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.output.TimeTable;
import server.models.Course;
import server.models.Date;
import server.models.DateId;
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
        List<Date> timeslotList = new ArrayList<>(10);
        timeslotList.add(new Date(new DateId(DayOfWeek.MONDAY, LocalTime.of(8, 30))));
        timeslotList.add(new Date(new DateId(DayOfWeek.MONDAY, LocalTime.of(9, 30))));
        timeslotList.add(new Date(new DateId(DayOfWeek.MONDAY, LocalTime.of(10, 30))));
        timeslotList.add(new Date(new DateId(DayOfWeek.MONDAY, LocalTime.of(13, 30))));
        timeslotList.add(new Date(new DateId(DayOfWeek.MONDAY, LocalTime.of(14, 30))));

        timeslotList.add(new Date(new DateId(DayOfWeek.TUESDAY, LocalTime.of(8, 30))));
        timeslotList.add(new Date(new DateId(DayOfWeek.TUESDAY, LocalTime.of(9, 30))));
        timeslotList.add(new Date(new DateId(DayOfWeek.TUESDAY, LocalTime.of(10, 30))));
        timeslotList.add(new Date(new DateId(DayOfWeek.TUESDAY, LocalTime.of(13, 30))));
        timeslotList.add(new Date(new DateId(DayOfWeek.TUESDAY, LocalTime.of(14, 30))));

        Department ufrInfo = new Department("info", "UFR d'informatique");
        RoomType td = new RoomType("TD");

        List<Room> roomList = new ArrayList<>(3);
        roomList.add(new Room("Room A", ufrInfo, 3, td));
        roomList.add(new Room("Room B", ufrInfo, 3, td));
        roomList.add(new Room("Room C", ufrInfo, 3, td));

        List<LessonOptaPlaner> lessonList = new ArrayList<>();
        long id = 0;

        Professor turing = new Professor("Turing", "Turing", "Jean", "jean@u-paris.fr", false,"mdpmdp");
        Professor curie = new Professor("M. Curie", "Curie", "Jeanne", "jeanne@u-paris.fr", false,"mdpmdp");
        Professor darwin = new Professor("C. Darwin", "Darwin", "Jeanne", "jeanne@u-paris.fr", false, "mdpmdp");
        Professor jones = new Professor("I. Jones", "Jones", "Jeanne", "jeanne@u-paris.fr", false, "mdpmdp");
        Professor cruz = new Professor("P. Cruz", "Cruz", "Jeanne", "jeanne@u-paris.fr", false, "mdpmdp");

        Degree grade9 = new Degree("9th grade", "9th grade");
        Degree grade10 = new Degree("10th grade", "10th grade");

        Course math9 = new Course(0, "Math", Set.of(grade9), Color.BLACK);
        Course physics9 = new Course(1, "Physics", Set.of(grade9), Color.BLACK);
        Course chemistry9 = new Course(2, "Chemistry", Set.of(grade9), Color.BLACK);
        Course biology9 = new Course(3, "Biology", Set.of(grade9), Color.BLACK);
        Course history9 = new Course(4, "History", Set.of(grade9), Color.BLACK);
        Course english9 = new Course(5, "English", Set.of(grade9), Color.BLACK);
        Course spanish9 = new Course(6, "Spanish", Set.of(grade9), Color.BLACK);
        Course math10 = new Course(7, "Math", Set.of(grade10), Color.BLACK);
        Course physics10 = new Course(8, "Physics", Set.of(grade10), Color.BLACK);
        Course chemistry10 = new Course(9, "Chemistry", Set.of(grade10), Color.BLACK);
        Course history10 = new Course(10, "History", Set.of(grade10), Color.BLACK);
        Course english10 = new Course(11, "English", Set.of(grade10), Color.BLACK);
        Course spanish10 = new Course(12, "Spanish", Set.of(grade10), Color.BLACK);
        Course french10 = new Course(13, "French", Set.of(grade10), Color.BLACK);
        Course geography10 = new Course(14, "Geography", Set.of(grade10), Color.BLACK);

        lessonList.add(new LessonOptaPlaner(id++, math9, turing, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, math9, turing, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, physics9, curie, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, chemistry9, curie, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, biology9, darwin, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, history9, jones, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, english9, jones, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, english9, jones, Duration.ofMinutes(120)));
        lessonList.add(new LessonOptaPlaner(id++, spanish9, cruz, Duration.ofMinutes(120)));
        lessonList.add(new LessonOptaPlaner(id++, spanish9, cruz, Duration.ofMinutes(120)));
        lessonList.add(new LessonOptaPlaner(id++, math10, turing, Duration.ofMinutes(120)));
        lessonList.add(new LessonOptaPlaner(id++, math10, turing, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, math10, turing, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, physics10, curie, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, chemistry10, curie, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, french10, curie, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, geography10, darwin, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, history10, jones, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, english10, cruz, Duration.ofMinutes(60)));
        lessonList.add(new LessonOptaPlaner(id++, spanish10, cruz, Duration.ofMinutes(60)));

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
