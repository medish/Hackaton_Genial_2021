package core.solver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.dataclasses.Room;
import core.dataclasses.Teacher;
import core.optaplaner.SolverOptaplaner;
import core.optaplaner.domain.LessonOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.output.TimeTable;
import core.output.Timeslot;

public class SolverOptaplanerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SolverOptaplanerTest.class);

	@Test
	void test() throws IOException {
		// Load the problem
		TimeTableOptaPlaner problem = generateDemoData();

		// Solve the problem
		TimeTable solution = new SolverOptaplaner().solve(problem.toOutput());

		// Visualize the solution
		assertEquals(IOUtils.toString(SolverOptaplanerTest.class.getResourceAsStream("/res.txt"), "UTF-8"),
				solution.toString());
	}

	public static TimeTableOptaPlaner generateDemoData() {
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

		return new TimeTableOptaPlaner(timeslotList, roomList, lessonList);
	}
}
