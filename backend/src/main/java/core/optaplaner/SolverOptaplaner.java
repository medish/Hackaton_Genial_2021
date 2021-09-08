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

package core.optaplaner;

import static java.util.stream.Collectors.groupingBy;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;

import core.dataclasses.Room;
import core.optaplaner.domain.LessonOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.optaplaner.solver.TimeTableConstraintProvider;
import core.output.Timeslot;

public class SolverOptaplaner {

	private static SolverFactory<TimeTableOptaPlaner> solverFactory = SolverFactory.create(
			new SolverConfig().withSolutionClass(TimeTableOptaPlaner.class).withEntityClasses(LessonOptaPlaner.class)
					.withConstraintProviderClass(TimeTableConstraintProvider.class)
					.withTerminationSpentLimit(Duration.ofSeconds(10)));

	public static TimeTableOptaPlaner solve(TimeTableOptaPlaner problem) throws IOException {
		Solver<TimeTableOptaPlaner> solver = solverFactory.buildSolver();
		return solver.solve(problem);
	}

	public static String printTimetable(TimeTableOptaPlaner timeTable) {
		StringBuilder stringBuilder = new StringBuilder();
		List<Room> roomList = timeTable.getRoomList();
		List<LessonOptaPlaner> lessonList = timeTable.getLessonList();
		Map<Timeslot, Map<Room, List<LessonOptaPlaner>>> lessonMap = lessonList.stream()
				.filter(lesson -> lesson.getTimeslot() != null && lesson.getRoom() != null)
				.collect(groupingBy(LessonOptaPlaner::getTimeslot, groupingBy(LessonOptaPlaner::getRoom)));
		stringBuilder.append("|            | " + roomList.stream().map(room -> String.format("%-10s", room.getName()))
				.collect(Collectors.joining(" | ")) + " |\n");
		stringBuilder.append("|" + "------------|".repeat(roomList.size() + 1) + "\n");
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
									cellLessonList.stream().map(LessonOptaPlaner::getSubject)
											.collect(Collectors.joining(", "))))
							.collect(Collectors.joining(" | "))
					+ " |\n");
			stringBuilder.append("|            | "
					+ cellList.stream()
							.map(cellLessonList -> String.format("%-10s",
									cellLessonList.stream().map(LessonOptaPlaner::getTeacher)
											.collect(Collectors.joining(", "))))
							.collect(Collectors.joining(" | "))
					+ " |\n");
			stringBuilder.append("|            | "
					+ cellList.stream()
							.map(cellLessonList -> String.format("%-10s",
									cellLessonList.stream().map(LessonOptaPlaner::getStudentGroup)
											.collect(Collectors.joining(", "))))
							.collect(Collectors.joining(" | "))
					+ " |\n");
			stringBuilder.append("|" + "------------|".repeat(roomList.size() + 1) + "\n");
		}
		List<LessonOptaPlaner> unassignedLessons = lessonList.stream()
				.filter(lesson -> lesson.getTimeslot() == null || lesson.getRoom() == null)
				.collect(Collectors.toList());
		if (!unassignedLessons.isEmpty()) {
			stringBuilder.append("");
			stringBuilder.append("Unassigned lessons");
			for (LessonOptaPlaner lesson : unassignedLessons) {
				stringBuilder.append("  " + lesson.getSubject() + " - " + lesson.getTeacher() + " - "
						+ lesson.getStudentGroup() + "\n");
			}
		}
		return stringBuilder.toString();
	}

}
