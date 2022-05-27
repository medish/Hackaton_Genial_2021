package core.optaplaner.solver;

import core.optaplaner.SolverOptaplaner;
import core.optaplaner.domain.CourseGroupOptaPlaner;
import core.selector.SelectorUnit;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;
import org.optaplanner.core.api.score.stream.bi.BiConstraintStream;
import org.optaplanner.core.api.score.stream.uni.UniConstraintStream;
import server.models.PrecedenceConstraint;
import server.models.RoomType;
import server.models.TimeConstraint;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeTableConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        List<Constraint> constraints = new ArrayList<>();
        constraints.add(roomConflict(constraintFactory));
        constraints.add(teacherConflict(constraintFactory));
        constraints.add(studentGroupConflict(constraintFactory));
        constraints.add(teacherRoomStability(constraintFactory));
        constraints.add(teacherTimeEfficiency(constraintFactory));
        constraints.addAll(SolverOptaplaner.getConstraints(constraintFactory));
        return constraints.toArray(new Constraint[0]);
    }

    public Constraint roomConflict(ConstraintFactory constraintFactory) {
        // A room can accommodate at most one lesson at the same time.
        return constraintFactory
                // Select each pair of 2 different Course groupe
                .forEachUniquePair(CourseGroupOptaPlaner.class,
                        // .... in the same timeSlot
                        Joiners.equal(CourseGroupOptaPlaner::getDateSlot),
                        // ... in the same room ...
                        Joiners.equal(CourseGroupOptaPlaner::getRoom))
                // ... and penalize each pair with a hard weight.
                .penalize("Room conflict", HardSoftScore.ONE_HARD);
    }

    public Constraint teacherConflict(ConstraintFactory constraintFactory) {
        // A teacher can teach at most one lesson at the same time.
        return constraintFactory
                .forEachUniquePair(CourseGroupOptaPlaner.class,
                        Joiners.equal(CourseGroupOptaPlaner::getDateSlot),
                        Joiners.equal(CourseGroupOptaPlaner::getProfessor))
                .penalize("Teacher conflict", HardSoftScore.ONE_HARD);
    }

    public Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach sequential lessons and dislikes gaps between lessons.
        return constraintFactory
                .forEach(CourseGroupOptaPlaner.class)
                .join(CourseGroupOptaPlaner.class,
                        Joiners.equal(CourseGroupOptaPlaner::getProfessor),
                        Joiners.equal(CourseGroupOptaPlaner::getDay))
                .filter((courseGroupe1, courseGroupe2) -> {
                    Duration between = Duration.between(courseGroupe1.getEndTime(), courseGroupe2.getStartTime());
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
                }).reward("Teacher time efficiency", HardSoftScore.ONE_SOFT);
    }

    public Constraint teacherRoomStability(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach in a single room.
        return constraintFactory
                .forEachUniquePair(CourseGroupOptaPlaner.class,
                        Joiners.equal(CourseGroupOptaPlaner::getProfessor))
                .filter((courseGroupe1, courseGroupe2) -> courseGroupe1.getRoom() != courseGroupe2.getRoom())
                .penalize("Teacher room stability", HardSoftScore.ONE_SOFT);
    }

    public Constraint studentGroupConflict(ConstraintFactory constraintFactory) {
        // A student can attend at most one lesson at the same time
        return constraintFactory
                .forEachUniquePair(CourseGroupOptaPlaner.class,
                        Joiners.equal(CourseGroupOptaPlaner::getDateSlot),
                        Joiners.equal(CourseGroupOptaPlaner::getCouseGroupe))
                .penalize("Student group conflict", HardSoftScore.ONE_HARD);
    }
    public Constraint teacherTimeJustAfterTwoLessonConflict(ConstraintFactory constraintFactory, String[] courses) {
        // A teacher prefer to teach lesson1 French just after lesson2 Chemistry
        return constraintFactory
                .forEach(CourseGroupOptaPlaner.class)
                .filter(courseGroupe -> courseGroupe.getMajorCourse().getCourse().getName().equals(courses[0]))
                .join(CourseGroupOptaPlaner.class)
                .filter((lesson, lesson2) -> lesson2.getMajorCourse().getCourse().getName().equals(courses[1]))
                .filter((lesson1, lesson2) -> lesson1.getStartTime().isBefore(lesson2.getStartTime()))
                .penalize("Teacher can't teach lesson 1 before lesson 2", HardSoftScore.ONE_SOFT);
    }

    public Constraint teacherLessonRoomTimePreferencesConflict(ConstraintFactory constraintFactory, String course, String profName, String romeName, int[] time) {
        // Teacher teacher1 'Turing' does not want to teach Course1 'Math' in room 'C' at Hour '8H30'
        return constraintFactory
                .forEach(CourseGroupOptaPlaner.class)
                .filter(courseGroupe -> courseGroupe.getMajorCourse().getCourse().getName().equals(course))
                .filter(courseGroupe -> courseGroupe.getProfessor().getLastName().equals(profName))
                .filter(courseGroupe-> courseGroupe.getStartTime().equals(LocalTime.of(time[0], time[1], time[2])))
                .filter(courseGroupe -> courseGroupe.getRoom().getName().equals(romeName))
                .penalize("Teacher 'profName' does not want to teach 'course' in room 'romName' at time", HardSoftScore.ONE_SOFT);
    }

    public Constraint teacherLessonRoomPreferencesConflict(ConstraintFactory constraintFactory, String course, String profName, String romeName) {
        // Teacher 'profName' does not want to teach 'course' in Room 'romName'
        return constraintFactory
                .forEach(CourseGroupOptaPlaner.class)
                .filter(courseGroupe -> courseGroupe.getMajorCourse().getCourse().getName().equals(course))
                .filter(courseGroupe -> courseGroupe.getProfessor().getLastName().equals(profName))
                .filter(courseGroupe -> courseGroupe.getRoom().getName().equals(romeName))
                .penalize("Teacher 'profName' does not want to teach 'course' in Room 'romName'", HardSoftScore.ONE_SOFT);
    }

    public Constraint teacherLessonNotInRoomPreferencesConflict(ConstraintFactory constraintFactory, String course, String profName, String romeName) {
        // Teacher 'profName' want to teach 'course' in Room 'romName'
        return constraintFactory
                .forEach(CourseGroupOptaPlaner.class)
                .filter(courseGroupe -> courseGroupe.getProfessor().getLastName().equals(profName))
                .filter(courseGroupe -> courseGroupe.getMajorCourse().getCourse().getName().equals(course))
                .filter(courseGroupe -> ! courseGroupe.getRoom().getName().equals(romeName))
                .penalize("Teacher 'profName' want to teach 'course' in Room 'romName'", HardSoftScore.ONE_SOFT);
    }

    public static Constraint techLessonBefore(ConstraintFactory constraintFactory, String courseName1, String courseName2) {
        // Lesson courseName1 avant courseName2 ( courseName1 -> courseName2 )
        return constraintFactory
                .forEach(CourseGroupOptaPlaner.class)
                .filter(courseGroupOptaPlaner -> courseGroupOptaPlaner.getMajorCourse().getCourse().getName().equals(courseName1))
                .join(CourseGroupOptaPlaner.class)
                .filter((courseGroupOptaPlaner, courseGroupOptaPlaner2) -> courseGroupOptaPlaner2.getMajorCourse().getCourse().getName().equals(courseName2))
                .filter((courseGroupOptaPlaner, courseGroupOptaPlaner2) -> courseGroupOptaPlaner.getStartTime().isAfter(courseGroupOptaPlaner2.getStartTime()))
                .penalize("Lesson courseName1 must be before courseName2", HardSoftScore.ONE_SOFT);
    }

    public static Constraint techLessonAfter(ConstraintFactory constraintFactory, String courseName1, String courseName2) {
        // Lesson courseName1 apres courseName2 ( courseName2 -> courseName1 )
        return constraintFactory
                .forEach(CourseGroupOptaPlaner.class)
                .filter(courseGroupOptaPlaner -> courseGroupOptaPlaner.getMajorCourse().getCourse().getName().equals(courseName1))
                .join(CourseGroupOptaPlaner.class)
                .filter((courseGroupOptaPlaner, courseGroupOptaPlaner2) -> courseGroupOptaPlaner2.getMajorCourse().getCourse().getName().equals(courseName2))
                .filter((courseGroupOptaPlaner, courseGroupOptaPlaner2) -> courseGroupOptaPlaner.getStartTime().isBefore(courseGroupOptaPlaner2.getStartTime()))
                .penalize("Lesson courseName1 must be after courseName2", HardSoftScore.ONE_SOFT);
    }

    /**
     * Precedence Constraint
     * @param constraintFactory
     * @param pc
     * @return
     */
    public static Constraint PrecedenceConstraint(ConstraintFactory constraintFactory, PrecedenceConstraint pc) {
        SelectorUnit[] firstSelectors = Arrays.stream(pc.getSelector().split(",")).map(SelectorUnit::builder)
                .toArray(SelectorUnit[]::new);

        SelectorUnit[] secondSelector = Arrays.stream(pc.getSelector().split(",")).map(SelectorUnit::builder)
                .toArray(SelectorUnit[]::new);

        UniConstraintStream<CourseGroupOptaPlaner> firstPart = constraintFactory.forEach(CourseGroupOptaPlaner.class);

        // filter with first selectors
        for (SelectorUnit selector : firstSelectors) {
            // loop teacher
            if ("teacher".equals(selector.getTable())) {
                if ("id".equals(selector.getAttribute())) {
                    firstPart = firstPart.filter(courseGroupe -> courseGroupe.getProfessor().getId()==Integer.parseInt(selector.getValue()));
                }
                if ("name".equals(selector.getAttribute())) {
                    firstPart = firstPart.filter(courseGroupe -> courseGroupe.getProfessor().getLastName().equals(selector.getValue()));
                }
            }

            // loop lesson
            if ("course".equals(selector.getTable())) {
                if ("id".equals(selector.getAttribute())) {
                    firstPart = firstPart.filter(courseGroupe -> courseGroupe.getId()==Integer.parseInt(selector.getValue()));
                }
                if ("name".equals(selector.getAttribute())) {
                    firstPart = firstPart.filter(courseGroupe -> courseGroupe.getMajorCourse().getCourse().getName().equals(selector.getValue()));
                }
            }
            // loop room
            if ("room".equals(selector.getTable())) {
                if ("name".equals(selector.getAttribute())) {
                    firstPart = firstPart.filter(courseGroupe -> courseGroupe.getRoom().getName().equals(selector.getValue()));
                }
                if ("roomType".equals(selector.getAttribute())) {
                    firstPart = firstPart
                            .filter(courseGroupe -> courseGroupe.getRoom().getRoomTypes().contains(RoomType.fromString(selector.getValue())));
                }
            }

        }

        // filter with second selector
        BiConstraintStream<CourseGroupOptaPlaner, CourseGroupOptaPlaner> merged = firstPart.join(CourseGroupOptaPlaner.class);

        for (SelectorUnit selector : secondSelector) {
            // loop teacher
            if ("teacher".equals(selector.getTable())) {
                if ("id".equals(selector.getAttribute())) {
                    merged = merged
                            .filter((courseGroupe, courseGroupe2) -> courseGroupe2.getProfessor().getId()==Integer.parseInt(selector.getValue()));
                }
                if ("name".equals(selector.getAttribute())) {
                    merged = merged
                            .filter((courseGroupe, courseGroupe2) -> courseGroupe2.getProfessor().getLastName().equals(selector.getValue()));
                }
            }

            // loop lesson
            if ("course".equals(selector.getTable())) {
                if ("id".equals(selector.getAttribute())) {
                    merged = merged.filter((courseGroupe, courseGroup2) -> courseGroup2.getId()==Integer.parseInt(selector.getValue()));
                }
                if ("name".equals(selector.getAttribute())) {
                    merged = merged.filter((courseGroupe, courseGroup2) -> courseGroup2.getMajorCourse().getCourse().getName().equals(selector.getValue()));
                }
            }

            // loop room
            if ("room".equals(selector.getTable())) {
                if ("name".equals(selector.getAttribute())) {
                    merged = merged
                            .filter((courseGroupe, courseGroupe2) -> courseGroupe2.getRoom().getName().equals(selector.getValue()));
                }
                if ("roomType".equals(selector.getAttribute())) {
                    merged = merged.filter(
                            (courseGroupe, courseGroupe2) -> courseGroupe2.getRoom().getRoomTypes().contains(RoomType.fromString(selector.getValue())));
                }
            }
        }

        // treating the constraint logic
        // checking when field -> avant/apres + strict
        String whenConstraint = pc.getWhenConstraint();


        if (whenConstraint.equals("avant")) {
            if (pc.isStrict()) {
                merged = merged.filter((courseGroupe1, courseGroupe2) -> courseGroupe1.isBeforeInDay(courseGroupe2)
                        && courseGroupe1.getEndTime().equals(courseGroupe2.getStartTime()));
            } else {
                merged = merged.filter((courseGroupe1, courseGroupe2) -> courseGroupe1.isBeforeInDay(courseGroupe2));
            }
        }
        //cours2 apres le cours1
        if (whenConstraint.equals("apres")) {
            if (pc.isStrict()) {
                merged = merged.filter((courseGroupe1, courseGroupe2) -> courseGroupe1.isAfterInDay(courseGroupe2)
                        && courseGroupe2.getEndTime().equals(courseGroupe1.getStartTime()));
            } else {
                merged = merged.filter((courseGroupe1, courseGroupe2) -> courseGroupe1.isAfterInDay(courseGroupe2));
            }
        }

        String displayedMsg = null;
        // verify the type of constraint -> give positive or negative score to the
        // constraint evaluation
        if (pc.isWants()) {// True
            displayedMsg = firstSelectors.toString() + " is " + whenConstraint + ' ' + secondSelector.toString();

            return merged.reward(displayedMsg, HardSoftScore.ofSoft(pc.getPriority()));
        }
        displayedMsg = firstSelectors.toString() + " is not" + whenConstraint + ' ' + secondSelector.toString();
        return merged.penalize(displayedMsg, HardSoftScore.ofSoft(pc.getPriority()));
    }

    /**
     * Time Constraint
     * @param constraintFactory
     * @param tc
     * @return
     */
    public static Constraint TimeConstraint(ConstraintFactory constraintFactory, TimeConstraint tc) {

        SelectorUnit[] firstSelectors = Arrays.stream(tc.getSelector().split(",")).map(SelectorUnit::builder)
                .toArray(SelectorUnit[]::new);

        UniConstraintStream<CourseGroupOptaPlaner> firstPart = constraintFactory.forEach(CourseGroupOptaPlaner.class);

        for (SelectorUnit selector : firstSelectors) {
            if ("teacher".equals(selector.getTable())) {
                if ("id".equals(selector.getAttribute())) {
                    firstPart = firstPart.filter(courseGroupe -> courseGroupe.getProfessor().getId()==Integer.parseInt(selector.getValue()));
                }
                if ("name".equals(selector.getAttribute())) {
                    firstPart = firstPart.filter(courseGroupe -> courseGroupe.getProfessor().getLastName().equals(selector.getValue()));
                }
            }

            if ("course".equals(selector.getTable())) {
                if ("id".equals(selector.getAttribute())) {
                    firstPart = firstPart.filter(courseGroupe -> courseGroupe.getId().equals(selector.getValue()));
                }
                if ("name".equals(selector.getAttribute())) {
                    firstPart = firstPart.filter(courseGroupe -> courseGroupe.getMajorCourse().getCourse().getName().equals(selector.getValue()));
                }
            }

            if ("room".equals(selector.getTable())) {
                if ("name".equals(selector.getAttribute())) {
                    firstPart = firstPart.filter(courseGroupe -> courseGroupe.getRoom().getName().equals(selector.getValue()));
                }
                if ("roomType".equals(selector.getAttribute())) {
                    firstPart = firstPart
                            .filter(courseGroupe -> courseGroupe.getRoom().getRoomTypes().contains(RoomType.fromString(selector.getValue())));
                }
            }
        }

        LocalTime startAt = tc.getStartTime();
        if (startAt != null) {
            firstPart = firstPart.filter(lesson -> lesson.getStartTime().equals(startAt));
        }

        String message = null;

        Boolean wants = tc.isWants();

        if (wants) {
            message = firstSelectors.toString() + " is " + "at" + tc.getStartTime();

            return firstPart.reward(message, HardSoftScore.ofSoft(tc.getPriority()));
        }
        message = firstSelectors.toString() + " is not" + "at" + tc.getStartTime();

        return firstPart.penalize(message, HardSoftScore.ofSoft(tc.getPriority()));
    }
}