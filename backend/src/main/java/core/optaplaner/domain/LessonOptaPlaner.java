package core.optaplaner.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import server.models.CourseSlot;

@PlanningEntity
public class LessonOptaPlaner extends AbstractPersistable implements FromInputToOptaPlaner<CourseSlot> {
 /*
    private CourseSlot courseSlot;
    private Professor teacher;
    private Duration duration;

    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private DateSlot timeslot;
    @PlanningVariable(valueRangeProviderRefs = "roomRange")
    private Room room;

    public LessonOptaPlaner() {
    }

    public LessonOptaPlaner(long id, CourseSlot courseSlot, Professor teacher) {
        super(id);
        this.courseSlot = courseSlot;
        this.teacher = teacher;
    }

    public LessonOptaPlaner(long id, CourseSlot courseSlot, Professor teacher, DateSlot timeslot, Room room) {
        this(id, courseSlot, teacher);
        this.timeslot = timeslot;
        this.room = room;
    }

    @Override
    public String toString() {
        return courseSlot + "(" + id + ")";
    }

    public CourseSlot getCourseSlot() {
        return courseSlot;
    }

    public Professor getTeacher() {
        return this.teacher;
    }

    public DateSlot getDate() {
        return timeslot;
    }

    public void setDate(DateSlot timeslot) {
        this.timeslot = timeslot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isBeforeInWeek(LessonOptaPlaner lesson) {
        return getDay().compareTo(lesson.getDay()) < 0;
    }

    public boolean isCollideInWeek(LessonOptaPlaner lesson) {
        return getDay().compareTo(lesson.getDay()) == 0;
    }

    public boolean isAfterInWeek(LessonOptaPlaner lesson) {
        return getDay().compareTo(lesson.getDay()) > 0;
    }

    public boolean isBeforeInDay(LessonOptaPlaner courseOpta) {
        return getDay().compareTo(lesson.getDay()) != 0
                || (courseSlot.get().plus(duration).isBefore(courseOpta.getStartTime()));
    }

    public boolean isCollide(LessonOptaPlaner courseOpta) {
        LocalTime startTime = courseSlot.getStartTime();

        if (getDay().compareTo(courseOpta.getDay()) != 0) {
            return false;
        }
        if (!startTime.isBefore(courseSlot.getStartTime()) && startTime.isBefore(courseSlot.getEndTime())) {
            return true;
        }
        if (!courseSlot.getStartTime().isBefore(startTime) && courseSlot.getStartTime().isBefore(startTime)) {
            return true;
        }
        return false;
    }

    public boolean isAfterInDay(LessonOptaPlaner courseOpta) {
        return getDay().compareTo(courseOpta.getDay()) != 0
                || (courseSlot.getStartTime().plus(duration).isAfter(courseSlot.getStartTime()));
    }

    public DayOfWeek getDay() {
        return timeslot.getDay();
    }

    public static LessonOptaPlaner fromInput(Lesson lesson) {
        Iterator<Professor> professors = lesson.getProfessors() != null ? lesson.getProfessors().iterator()
                : new HashSet<Professor>().iterator();
        LessonOptaPlaner lessonOptaPlaner = new LessonOptaPlaner(lesson.getId().hashCode(), lesson.getCourse(),
                professors.hasNext() ? professors.next() : null, lesson.getDuration());
        return lessonOptaPlaner;
    }

    public static LessonOptaPlaner fromInput(CourseSlot output) {
        Iterator<Professor> professors = output.getProfessors() != null ? output.getProfessors().iterator()
                : new HashSet<Professor>().iterator();
        LessonOptaPlaner lessonOptaPlaner = new LessonOptaPlaner(output.getId().hashCode(), output.getCourse(),
                professors.hasNext() ? professors.next() : null, output.getDuration(), output.getDate(),
                output.getRoom());
        return lessonOptaPlaner;
    }lesson

    public CourseSlot toOutput() {
        return new CourseSlot(timeslot, room,
                new Lesson(id + "", duration, 0, courseSlot, null, teacher == null ? null : Set.of(teacher)));
    }

  */
}
