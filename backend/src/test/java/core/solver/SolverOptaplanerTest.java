package core.solver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.score.ScoreExplanation;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import core.SolverTimeTable;
import core.dataclasses.Room;
import core.dataclasses.Teacher;
import core.optaplaner.SolverOptaplaner;
import core.optaplaner.domain.LessonOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.output.TimeTable;
import core.output.Timeslot;

public class SolverOptaplanerTest {

    private TimeTableOptaPlaner problem;
    private SolverTimeTable solverTimeTable;

    @BeforeEach
    void before() {
        generateDemoData();
        solverTimeTable = new SolverOptaplaner();
    }

    public void generateDemoData() {
        List<Timeslot> timeslotList = new ArrayList<>(10);
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));

        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));

        List<Room> roomList = new ArrayList<>(3);
        roomList.add(new Room("Room A"));
        roomList.add(new Room("Room B"));
        roomList.add(new Room("Room C"));

        List<LessonOptaPlaner> lessonList = new ArrayList<>();
        long id = 0;

        Teacher turing = new Teacher("Turing");

        lessonList.add(new LessonOptaPlaner(id++, "Math", turing, "9th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "Math", turing, "9th grade"));
        Teacher curie = new Teacher("M. Curie");

        lessonList.add(new LessonOptaPlaner(id++, "Physics", curie, "9th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "Chemistry", curie, "9th grade"));

        Teacher darwin = new Teacher("C. Darwin");
        lessonList.add(new LessonOptaPlaner(id++, "Biology", darwin, "9th grade"));

        Teacher jones = new Teacher("I. Jones");
        lessonList.add(new LessonOptaPlaner(id++, "History", jones, "9th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "English", jones, "9th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "English", jones, "9th grade"));
        Teacher cruz = new Teacher("P. Cruz");
        lessonList.add(new LessonOptaPlaner(id++, "Spanish", cruz, "9th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "Spanish", cruz, "9th grade"));

        lessonList.add(new LessonOptaPlaner(id++, "Math", turing, "10th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "Math", turing, "10th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "Math", turing, "10th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "Physics", curie, "10th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "Chemistry", curie, "10th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "French", curie, "10th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "Geography", darwin, "10th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "History", jones, "10th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "English", cruz, "10th grade"));
        lessonList.add(new LessonOptaPlaner(id++, "Spanish", cruz, "10th grade"));

        problem = new TimeTableOptaPlaner(timeslotList, roomList, lessonList);
    }

    @Test
    void solveTest() throws IOException {
        TimeTable solution = solverTimeTable.solve(problem.toOutput());

        assertEquals(IOUtils.toString(SolverOptaplanerTest.class.getResourceAsStream("/res.txt"), "UTF-8"),
                solution.toString());
    }

    @Test
    void verifyTest() throws IOException {
        ScoreExplanation<TimeTableOptaPlaner, HardSoftScore> verify = solverTimeTable
                .verify(solverTimeTable.solve(problem.toOutput()));

        assertEquals("""
                Explanation of score (0hard/0soft):
                    Constraint match totals:
                        -1soft: constraint (Teacher room stability) has 1 matches:
                            -1soft: justifications ([Biology(4), Geography(16)])
                        -1soft: constraint (Teacher Turing does not want to teach Math in room C at 8H30) has 1 matches:
                            -1soft: justifications ([Math(11)])
                        -1soft: constraint (Teacher M. Curie does not want to teach French in Room A) has 1 matches:
                            -1soft: justifications ([French(15)])
                        3soft: constraint (Teacher time efficiency) has 3 matches:
                            1soft: justifications ([Spanish(9), Spanish(8)])
                            1soft: justifications ([Math(11), Math(1)])
                            ...
                    Indictments (top 5 of 9):
                        -1soft: justification (Biology(4)) has 1 matches:
                            -1soft: constraint (Teacher room stability)
                        -1soft: justification (Geography(16)) has 1 matches:
                            -1soft: constraint (Teacher room stability)
                        -1soft: justification (French(15)) has 1 matches:
                            -1soft: constraint (Teacher M. Curie does not want to teach French in Room A)
                        0: justification (Math(11)) has 2 matches:
                            -1soft: constraint (Teacher Turing does not want to teach Math in room C at 8H30)
                            1soft: constraint (Teacher time efficiency)
                        1soft: justification (Spanish(9)) has 1 matches:
                            1soft: constraint (Teacher time efficiency)
                        ...
                """, verify.toString());
    }
}
