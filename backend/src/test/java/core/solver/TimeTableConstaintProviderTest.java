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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import core.dataclasses.Room;
import core.dataclasses.Teacher;
import core.optaplaner.SolverOptaplaner;
import core.optaplaner.domain.LessonOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.output.Timeslot;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.score.stream.Joiners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

		List<Teacher> teacherList = List.of(
				curie, cruz, turing, jones, darwin
		);
		return new TimeTableOptaPlaner(timeslotList, roomList, lessonList, teacherList);
	}

	private static String printTimetable(TimeTableOptaPlaner timeTable) {
		StringBuilder stringBuilder =new StringBuilder();
		List<Room> roomList = timeTable.getRoomList();
		List<LessonOptaPlaner> lessonList = timeTable.getLessonList();
		Map<Timeslot, Map<Room, List<LessonOptaPlaner>>> lessonMap = lessonList.stream()
				.filter(lesson -> lesson.getTimeslot() != null && lesson.getRoom() != null)
				.collect(Collectors.groupingBy(LessonOptaPlaner::getTimeslot, Collectors.groupingBy(LessonOptaPlaner::getRoom)));
		stringBuilder.append("|            | " + roomList.stream().map(room -> String.format("%-10s", room.getName()))
				.collect(Collectors.joining(" | ")) + " |\n");
		stringBuilder.append("|" + "------------|".repeat(roomList.size() + 1)+"\n");
		for (Timeslot timeslot : timeTable.getTimeslotList()) {
			List<List<LessonOptaPlaner>> cellList = roomList.stream().map(room -> {
				Map<Room, List<LessonOptaPlaner>> byRoomMap = lessonMap.get(timeslot);
				if (byRoomMap == null) {
					return Collections.<LessonOptaPlaner>emptyList();
				}
				List<LessonOptaPlaner> cellLessonList = byRoomMap.get(room);
				if (cellLessonList == null) {
					return Collections.<LessonOptaPlaner>emptyList();
				}
				return cellLessonList;
			}).collect(Collectors.toList());

			stringBuilder.append("| "
					+ String.format(
							"%-10s", timeslot.getDayOfWeek().toString().substring(0, 3) + " " + timeslot.getStartTime())
					+ " | "
					+ cellList.stream()
							.map(cellLessonList -> String.format("%-10s",
									cellLessonList.stream().map(LessonOptaPlaner::getSubject).collect(Collectors.joining(", "))))
							.collect(Collectors.joining(" | "))
					+ " |\n");
			stringBuilder.append("|            | " + cellList.stream()
					.map(cellLessonList -> String.format("%-10s",
							cellLessonList.stream().map(LessonOptaPlaner::getTeacher).map(Teacher::getName).collect(Collectors.joining(", "))))
					.collect(Collectors.joining(" | ")) + " |\n");
			stringBuilder.append("|            | "
					+ cellList.stream()
							.map(cellLessonList -> String.format("%-10s",
									cellLessonList.stream().map(LessonOptaPlaner::getStudentGroup)
											.collect(Collectors.joining(", "))))
							.collect(Collectors.joining(" | "))
					+ " |\n");
			stringBuilder.append("|" + "------------|".repeat(roomList.size() + 1)+"\n");
		}
		List<LessonOptaPlaner> unassignedLessons = lessonList.stream()
				.filter(lesson -> lesson.getTimeslot() == null || lesson.getRoom() == null)
				.collect(Collectors.toList());
		if (!unassignedLessons.isEmpty()) {
			stringBuilder.append("");
			stringBuilder.append("Unassigned lessons");
			for (LessonOptaPlaner lesson : unassignedLessons) {
				stringBuilder.append(
						"  " + lesson.getSubject() + " - " + lesson.getTeacher() + " - " + lesson.getStudentGroup()+"\n");
			}
		}
		return stringBuilder.toString();
	}
}
