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

package core.optaplaner.solver;

import java.time.Duration;
import java.time.LocalTime;

import core.domain.Room;
import core.domain.Timeslot;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

import core.optaplaner.domain.LessonOptaPlaner;

public class TimeTableConstraintProvider implements ConstraintProvider {

	@Override
	public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
		return new Constraint[] {
				// Hard constraints
				roomConflict(constraintFactory), teacherConflict(constraintFactory),
				studentGroupConflict(constraintFactory),
				// Soft constraints
				teacherRoomStability(constraintFactory), teacherTimeEfficiency(constraintFactory),
				teacherTimeJustAfterTwoLessonConflict(constraintFactory),
				teacherLessonRoomTimePreferencesConflict(constraintFactory),
				teacherLessonRoomPreferencesConflict(constraintFactory)
				};
	}

	Constraint roomConflict(ConstraintFactory constraintFactory) {
		// A room can accommodate at most one lesson at the same time.
		return constraintFactory
				// Select each pair of 2 different lessons ...
				.fromUniquePair(LessonOptaPlaner.class,
						// ... in the same timeslot ...
						Joiners.equal(LessonOptaPlaner::getTimeslot),
						// ... in the same room ...
						Joiners.equal(LessonOptaPlaner::getRoom))
				// ... and penalize each pair with a hard weight.
				.penalize("Room conflict", HardSoftScore.ONE_HARD);
	}

	Constraint teacherConflict(ConstraintFactory constraintFactory) {
		// A teacher can teach at most one lesson at the same time.
		return constraintFactory
				.fromUniquePair(LessonOptaPlaner.class, Joiners.equal(LessonOptaPlaner::getTimeslot),
						Joiners.equal(LessonOptaPlaner::getTeacher))
				.penalize("Teacher conflict", HardSoftScore.ONE_HARD);
	}

	Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {
		// A teacher prefers to teach sequential lessons and dislikes gaps between
		// lessons.
		return constraintFactory.from(LessonOptaPlaner.class).join(LessonOptaPlaner.class, Joiners.equal(LessonOptaPlaner::getTeacher),
				Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek())).filter((lesson1, lesson2) -> {
			Duration between = Duration.between(lesson1.getTimeslot().getEndTime(),
					lesson2.getTimeslot().getStartTime());
			return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
		}).reward("Teacher time efficiency", HardSoftScore.ONE_SOFT);
	}

	Constraint teacherRoomStability(ConstraintFactory constraintFactory) {
		// A teacher prefers to teach in a single room.
		return constraintFactory.fromUniquePair(LessonOptaPlaner.class, Joiners.equal(LessonOptaPlaner::getTeacher))
				.filter((lesson1, lesson2) -> lesson1.getRoom() != lesson2.getRoom())
				.penalize("Teacher room stability", HardSoftScore.ONE_SOFT);
	}

	Constraint studentGroupConflict(ConstraintFactory constraintFactory) {
		// A student can attend at most one lesson at the same time.
		return constraintFactory
				.fromUniquePair(LessonOptaPlaner.class, Joiners.equal(LessonOptaPlaner::getTimeslot),
						Joiners.equal(LessonOptaPlaner::getStudentGroup))
				.penalize("Student group conflict", HardSoftScore.ONE_HARD);
	}

	Constraint teacherTimeJustAfterTwoLessonConflict(ConstraintFactory constraintFactory) {
		// A teacher prefer to teach lesson French just after lesson Chemistry
		return constraintFactory.from(LessonOptaPlaner.class)
				.filter(lesson -> lesson.getSubject().equals("Chemistry"))
				.join(LessonOptaPlaner.class)
				//Date Fin de lesson 1 = Date Debon de lesson 2
				.filter((lesson, lesson2) -> lesson2.getSubject().equals("French"))
				.filter((lesson1, lesson2) -> lesson1.getTimeslot().getEndTime()==lesson2.getTimeslot().getStartTime())
				.penalize("Teacher can't teach lesson 1 before lesson 2", HardSoftScore.ONE_SOFT);
	}

	 Constraint teacherLessonRoomTimePreferencesConflict(ConstraintFactory constraintFactory) {
		 // Teacher Turing does not want to teach Math in room C at 8H30
		return constraintFactory.from(LessonOptaPlaner.class)
				.filter(lesson -> lesson.getSubject().equals("Math"))
				.filter((lesson) -> lesson.getTeacher().getName().equals("Turing"))
				.filter((lesson) -> lesson.getTimeslot().getStartTime().equals(LocalTime.of(8, 30)))
				.filter((lesson) -> lesson.getRoom().getName().equals("Room C"))
				.penalize("Teacher Turing does not want to teach Math in room C at 8H30", HardSoftScore.ONE_SOFT);
	}

	Constraint teacherLessonRoomPreferencesConflict(ConstraintFactory constraintFactory) {
		// Teacher M. Curie does not want to teach French in Room A
		return constraintFactory.from(LessonOptaPlaner.class)
				.filter(lesson -> lesson.getSubject().equals("French"))
				.filter((lesson) -> lesson.getTeacher().getName().equals("M. Curie"))
				.filter((lesson) -> lesson.getRoom().getName().equals("Room A"))
				.penalize("Teacher M. Curie does not want to teach French in Room A", HardSoftScore.ONE_SOFT);
	}

}