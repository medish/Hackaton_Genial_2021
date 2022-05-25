package core.optaplaner.solver;

import core.optaplaner.SolverOptaplaner;
import core.optaplaner.domain.LessonOptaPlaner;
import core.selector.SelectorUnit;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;
import org.optaplanner.core.api.score.stream.uni.UniConstraintStream;
import server.models.PrecedenceConstraint;
import server.models.Professor;
import server.models.TimeConstraint;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
                // Select each pair of 2 different lessons ...
                .fromUniquePair(LessonOptaPlaner.class,
                        // ... in the same room ...
                        Joiners.equal(LessonOptaPlaner::getRoom),
                        // ... in the same timeslot ...
                        Joiners.filtering((lesson1, lesson2) -> lesson1.isCollide(lesson2)))
                // ... and penalize each pair with a hard weight.
                .penalize("Room conflict", HardSoftScore.ONE_HARD);
    }

    public Constraint teacherConflict(ConstraintFactory constraintFactory) {
        // A teacher can teach at most one lesson at the same time.
        return constraintFactory
                .fromUniquePair(LessonOptaPlaner.class, Joiners
                        .equal(lesson -> Optional.ofNullable(lesson.getTeacher()).map(Professor::getLastName).orElse(null)),
                        Joiners.filtering((lesson1, lesson2) -> lesson1.isCollide(lesson2)))
                .penalize("Teacher conflict", HardSoftScore.ONE_HARD);
    }

    public Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach sequential lessons and dislikes gaps between
        // lessons.
        return constraintFactory.from(LessonOptaPlaner.class).join(LessonOptaPlaner.class,
                Joiners.equal(LessonOptaPlaner::getTeacher), Joiners.equal(LessonOptaPlaner::getDay))
                .filter((lesson1, lesson2) -> {
                    Duration between = Duration.between(lesson1.getEndTime(), lesson2.getEndTime());
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
                }).reward("Teacher time efficiency", HardSoftScore.ONE_SOFT);
    }

    public Constraint teacherRoomStability(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach in a single room.
        return constraintFactory.fromUniquePair(LessonOptaPlaner.class, Joiners.equal(LessonOptaPlaner::getTeacher))
                .filter((lesson1, lesson2) -> lesson1.getRoom() != lesson2.getRoom())
                .penalize("Teacher room stability", HardSoftScore.ONE_SOFT);
    }

    public Constraint studentGroupConflict(ConstraintFactory constraintFactory) {
        // A student can attend at most one lesson at the same time.
        return constraintFactory
                .fromUniquePair(LessonOptaPlaner.class,
                        Joiners.equal(lesson -> lesson.getSubject().getDegrees().iterator().next().getName()),
                        Joiners.filtering((lesson1, lesson2) -> lesson1.isCollide(lesson2)))
                .penalize("Student group conflict", HardSoftScore.ONE_HARD);
    }
//
//    public Constraint teacherTimeJustAfterTwoLessonConflict(ConstraintFactory constraintFactory) {
//        // A teacher prefer to teach lesson French just after lesson Chemistry
//        return constraintFactory.from(LessonOptaPlaner.class).filter(lesson -> lesson.getSubject().equals("Chemistry"))
//                .join(LessonOptaPlaner.class).filter((lesson, lesson2) -> lesson2.getSubject().equals("French"))
//                .filter((lesson1, lesson2) -> lesson1.getDate().getHour().isAfter(lesson2.getDate().getHour())
//                        || lesson1.getDate().getDay().compareTo(lesson2.getDate().getDay()) >= 0)
//                .penalize("Teacher can't teach lesson 1 before lesson 2", HardSoftScore.ONE_SOFT);
//    }
//
//    public static Constraint teacherLessonRoomTimePreferencesConflict(ConstraintFactory constraintFactory) {
//        // Teacher Turing does not want to teach Math in room C at 8H30
//        return constraintFactory.from(LessonOptaPlaner.class).filter(lesson -> lesson.getSubject().equals("Math"))
//                .filter((lesson) -> lesson.getTeacher().getName().equals("Turing"))
//                .filter((lesson) -> lesson.getStartTime().equals(LocalTime.of(8, 30)))
//                .filter((lesson) -> lesson.getRoom().getNumber().equals("Room C"))
//                .penalize("Teacher Turing does not want to teach Math in room C at 8H30", HardSoftScore.ONE_SOFT);
//    }
//
//    public static Constraint teacherLessonRoomPreferencesConflict(ConstraintFactory constraintFactory) {
//        // Teacher M. Curie does not want to teach French in Room A
//        return constraintFactory.from(LessonOptaPlaner.class).filter(lesson -> lesson.getSubject().equals("French"))
//                .filter((lesson) -> lesson.getTeacher().getName().equals("M. Curie"))
//                .filter((lesson) -> lesson.getRoom().getNumber().equals("Room A"))
//                .penalize("Teacher M. Curie does not want to teach French in Room A", HardSoftScore.ONE_SOFT);
//    }
//
//    public static Constraint techLessonBefore(ConstraintFactory constraintFactory) {
//
//        // Lesson Math before french ( Math -> francais )
//        return constraintFactory.from(LessonOptaPlaner.class)
//                .filter(lessonOptaPlaner -> lessonOptaPlaner.getSubject().equals("Math")).join(LessonOptaPlaner.class)
//                .filter((lessonOptaPlaner, lessonOptaPlaner2) -> lessonOptaPlaner2.getSubject().equals("French"))
//                .filter((lessonOptaPlaner, lessonOptaPlaner2) -> lessonOptaPlaner.getDate().getHour()
//                        .isAfter(lessonOptaPlaner2.getDate().getHour())
//                        || lessonOptaPlaner.getDate().getDay().compareTo(lessonOptaPlaner2.getDate().getDay()) >= 0)
//                .penalize("Lesson Math must be before french", HardSoftScore.ONE_SOFT);
//
//    }

    public static Constraint PrecedenceConstraint(ConstraintFactory constraintFactory, PrecedenceConstraint pc) {
        SelectorUnit[] firstSelectors = Arrays.stream(pc.getSelector().split(",")).map(SelectorUnit::builder)
                .toArray(SelectorUnit[]::new);
        SelectorUnit[] secondSelector = Arrays.stream(pc.getSelector().split(",")).map(SelectorUnit::builder)
                .toArray(SelectorUnit[]::new);

        UniConstraintStream<LessonOptaPlaner> firstPart = constraintFactory.from(LessonOptaPlaner.class);

        // filter with first selectors

        for (SelectorUnit selector : firstSelectors) {

            // loop teacher
            if (selector.getTable() == "teacher") {

                if (selector.getAttribute() == "id") {
                    firstPart = firstPart.filter(lesson -> lesson.getTeacher().getId().equals(selector.getValue()));
                }
                if (selector.getAttribute() == "name") {
                    firstPart = firstPart.filter(lesson -> lesson.getTeacher().getName().equals(selector.getValue()));
                }
            }

            // loop lesson
            if (selector.getTable() == "lesson") {
                if (selector.getAttribute() == "id") {
                    firstPart = firstPart.filter(lesson -> lesson.getId().equals(selector.getValue()));
                }
                if (selector.getAttribute() == "subject") {
                    firstPart = firstPart.filter(lesson -> lesson.getSubject().equals(selector.getValue()));
                }
            }

            // loop room
            if (selector.getTable() == "room") {
                if (selector.getAttribute() == "number") {
                    firstPart = firstPart.filter(lesson -> lesson.getRoom().getNumber().equals(selector.getValue()));
                }
                if (selector.getAttribute() == "roomType_id") {
                    firstPart = firstPart
                            .filter(lesson -> lesson.getRoom().getRoomType().getName().equals(selector.getValue()));
                }
            }

        }

        // filter with second selector
        BiConstraintStream<LessonOptaPlaner, LessonOptaPlaner> merged = firstPart.join(LessonOptaPlaner.class);
        for (SelectorUnit selector : secondSelector) {

            // loop teacher
            if (selector.getTable() == "teacher") {

                if (selector.getAttribute() == "id") {
                    merged = merged
                            .filter((lesson, lesson2) -> lesson2.getTeacher().getId().equals(selector.getValue()));
                }
                if (selector.getAttribute() == "name") {
                    merged = merged
                            .filter((lesson, lesson2) -> lesson2.getTeacher().getName().equals(selector.getValue()));
                }
            }

            // loop lesson
            if (selector.getTable() == "lesson") {
                if (selector.getAttribute() == "id") {
                    merged = merged.filter((lesson, lesson2) -> lesson2.getId().equals(selector.getValue()));
                }
                if (selector.getAttribute() == "subject") {
                    merged = merged.filter((lesson, lesson2) -> lesson2.getSubject().equals(selector.getValue()));
                }
            }

            // loop room
            if (selector.getTable() == "room") {
                if (selector.getAttribute() == "number") {
                    merged = merged
                            .filter((lesson, lesson2) -> lesson2.getRoom().getNumber().equals(selector.getValue()));
                }
                if (selector.getAttribute() == "roomType_id") {
                    merged = merged.filter(
                            (lesson, lesson2) -> lesson2.getRoom().getRoomType().getName().equals(selector.getValue()));
                }
            }

        }

        // treating the constraint logic
        // checking when field -> avant/pendant/apres + strict
        String when = pc.getWhen();

        if (when.equals("avant")) {
            if (pc.isStrict()) {
                merged = merged.filter((lesson1, lesson2) -> lesson1.isBeforeInDay(lesson2)
                        && lesson1.getEndTime().equals(lesson2.getStartTime()));
            } else {
                merged = merged.filter((lesson1, lesson2) -> lesson1.isBeforeInDay(lesson2));
            }
        }
        if (when.equals("pendant")) {
            merged = merged.filter((lesson1, lesson2) -> lesson1.isCollide(lesson2));
        }
        if (when.equals("apres")) {
            if (pc.isStrict()) {
                merged = merged.filter((lesson1, lesson2) -> lesson1.isBeforeInDay(lesson2)
                        && lesson1.getStartTime().equals(lesson2.getEndTime()));
            } else {
                merged = merged.filter((lesson1, lesson2) -> lesson1.isAfterInDay(lesson2));
            }
        }

        String displayedMsg = null;
        // verify the type of constraint -> give positive or negative score to the
        // constraint evaluation
        if (pc.isWants()) {// True
            displayedMsg = firstSelectors.toString() + " is " + when + ' ' + secondSelector.toString();

            return merged.reward(displayedMsg, HardSoftScore.ofSoft(pc.getPriority()));
        }
        displayedMsg = firstSelectors.toString() + " is not" + when + ' ' + secondSelector.toString();
        return merged.penalize(displayedMsg, HardSoftScore.ofSoft(pc.getPriority()));
    }

    public static Constraint TimeConstraint(ConstraintFactory constraintFactory, TimeConstraint tc) {

        SelectorUnit[] firstSelectors = Arrays.stream(tc.getSelector().split(",")).map(SelectorUnit::builder)
                .toArray(SelectorUnit[]::new);

        UniConstraintStream<LessonOptaPlaner> firstPart = constraintFactory.from(LessonOptaPlaner.class);

        for (SelectorUnit selector : firstSelectors) {
            if (selector.getTable() == "teacher") {

                if (selector.getAttribute() == "id") {
                    firstPart = firstPart.filter(lesson -> lesson.getTeacher().getId().equals(selector.getValue()));
                }
                if (selector.getAttribute() == "name") {
                    firstPart = firstPart.filter(lesson -> lesson.getTeacher().getName().equals(selector.getValue()));
                }
            }

            if (selector.getTable() == "lesson") {
                if (selector.getAttribute() == "id") {
                    firstPart = firstPart.filter(lesson -> lesson.getId().equals(selector.getValue()));
                }
                if (selector.getAttribute() == "subject") {
                    firstPart = firstPart.filter(lesson -> lesson.getSubject().equals(selector.getValue()));
                }
            }

            if (selector.getTable() == "room") {
                if (selector.getAttribute() == "number") {
                    firstPart = firstPart.filter(lesson -> lesson.getRoom().getNumber().equals(selector.getValue()));
                }
                if (selector.getAttribute() == "roomType_id") {
                    firstPart = firstPart
                            .filter(lesson -> lesson.getRoom().getRoomType().getName().equals(selector.getValue()));
                }
            }
        }

        LocalTime start_at = tc.getStart_time();
        if (start_at != null) {
            firstPart = firstPart.filter(lesson -> lesson.getStartTime().equals(start_at));
        }

        String message = null;

        Boolean wants = tc.getWants();

        if (wants) {
            message = firstSelectors.toString() + " is " + "at" + tc.getStart_time();

            return firstPart.reward(message, HardSoftScore.ofSoft(tc.getPriority()));
        }
        message = firstSelectors.toString() + " is not" + "at" + tc.getStart_time();

        return firstPart.penalize(message, HardSoftScore.ofSoft(tc.getPriority()));
    }
}