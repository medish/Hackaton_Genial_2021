/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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

package core.output;

import java.util.List;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import core.dataclasses.Room;
import core.optaplaner.domain.LessonOptaPlaner;

public class TimeTable implements Output {

	private List<Timeslot> timeslotList;
	private List<Room> roomList;
	private List<LessonOptaPlaner> lessonList;

	private HardSoftScore score;

	public TimeTable() {
	}

	public TimeTable(List<Timeslot> timeslotList, List<Room> roomList, List<LessonOptaPlaner> lessonList) {
		this.timeslotList = timeslotList;
		this.roomList = roomList;
		this.lessonList = lessonList;
	}

	public List<Timeslot> getTimeslotList() {
		return timeslotList;
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public List<LessonOptaPlaner> getLessonList() {
		return lessonList;
	}

	public HardSoftScore getScore() {
		return score;
	}

}