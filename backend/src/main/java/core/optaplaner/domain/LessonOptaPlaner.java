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

package core.optaplaner.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import core.dataclasses.Lesson;
import core.dataclasses.Room;
import core.output.Timeslot;
import core.dataclasses.Teacher;
@PlanningEntity
public class LessonOptaPlaner extends AbstractPersistable implements FromInputToOptaPlaner<Lesson> {

	private String subject;
	private Teacher teacher;
	private String studentGroup;

	@PlanningVariable(valueRangeProviderRefs = "timeslotRange")
	private Timeslot timeslot;
	@PlanningVariable(valueRangeProviderRefs = "roomRange")
	private Room room;

	public LessonOptaPlaner() {
	}


	public LessonOptaPlaner(long id, String subject, Teacher teacher, String studentGroup) {
		super(id);
		this.subject = subject;
		this.teacher = teacher;
		this.studentGroup = studentGroup;
	}

	public LessonOptaPlaner(long id, String subject, Teacher teacher, String studentGroup, Timeslot timeslot, Room room) {
		this(id, subject, teacher, studentGroup);
		this.timeslot = timeslot;
		this.room = room;
	}

	@Override
	public String toString() {
		return subject + "(" + id + ")";
	}

	public String getSubject() {
		return subject;
	}

	public Teacher getTeacher() {

		return this.teacher;
	}

	public String getStudentGroup() {
		return studentGroup;
	}

	public Timeslot getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(Timeslot timeslot) {
		this.timeslot = timeslot;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public Lesson fromInput() {
		// TODO Auto-generated method stub
		return null;
	}
}