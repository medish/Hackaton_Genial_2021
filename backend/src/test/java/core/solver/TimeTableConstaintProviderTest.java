/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import core.optaplaner.SolverOptaplaner;
import core.optaplaner.domain.LessonOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.output.Timeslot;

public class TimeTableConstaintProviderTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimeTableConstaintProviderTest.class);

	@Test
	void test() throws IOException {
		// Load the problem
		TimeTableOptaPlaner problem = generateDemoData();

		// Solve the problem
		TimeTableOptaPlaner solution = SolverOptaplaner.solve(problem);

		// Visualize the solution
		assertEquals(IOUtils.toString(TimeTableConstaintProviderTest.class.getResourceAsStream("/res.txt"), "UTF-8"),
				SolverOptaplaner.printTimetable(solution));
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
		lessonList.add(new LessonOptaPlaner(id++, "Math", "A. Turing", "9th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "Math", "A. Turing", "9th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "Physics", "M. Curie", "9th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "Chemistry", "M. Curie", "9th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "Biology", "C. Darwin", "9th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "History", "I. Jones", "9th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "English", "I. Jones", "9th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "English", "I. Jones", "9th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "Spanish", "P. Cruz", "9th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "Spanish", "P. Cruz", "9th grade"));

		lessonList.add(new LessonOptaPlaner(id++, "Math", "A. Turing", "10th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "Math", "A. Turing", "10th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "Math", "A. Turing", "10th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "Physics", "M. Curie", "10th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "Chemistry", "M. Curie", "10th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "French", "M. Curie", "10th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "Geography", "C. Darwin", "10th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "History", "I. Jones", "10th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "English", "P. Cruz", "10th grade"));
		lessonList.add(new LessonOptaPlaner(id++, "Spanish", "P. Cruz", "10th grade"));

		return new TimeTableOptaPlaner(timeslotList, roomList, lessonList);
	}
}
