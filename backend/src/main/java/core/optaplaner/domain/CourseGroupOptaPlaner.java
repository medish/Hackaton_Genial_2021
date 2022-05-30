package core.optaplaner.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import server.models.*;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@PlanningEntity
public class CourseGroupOptaPlaner extends AbstractPersistable implements FromInputToOptaPlaner<CourseGroup> {

    private Professor professor;
    private CourseGroup courseGroup;

    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private DateSlot dateSlot;
    @PlanningVariable(valueRangeProviderRefs = "roomRange")
    private Room room;

    public CourseGroupOptaPlaner() {
    }

    public CourseGroupOptaPlaner(long id, CourseGroup courseGroup, Professor professor) {
        super(id);
        this.courseGroup = courseGroup;
        this.professor = professor;
    }

    public CourseGroupOptaPlaner(long id, CourseGroup courseGroup, Professor professor, DateSlot dateSlot, Room room) {
        this(id, courseGroup, professor);
        this.dateSlot = dateSlot;
        this.room = room;
    }

    @Override
    public String toString() {
        return getMajorCourse() + "(" + id + ")";
    }

    public MajorCourse getMajorCourse() {
        return courseGroup.getMajorCourse();
    }

    public Duration getDuration() {
        return courseGroup.getDuration();
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public DateSlot getDateSlot() {
        return dateSlot;
    }

    public void setDateSlot(DateSlot dateSlot) {
        this.dateSlot = dateSlot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isBeforeInWeek(CourseGroupOptaPlaner courseGroup) {
        return getDay().compareTo(courseGroup.getDay()) < 0;
    }

    public boolean isCollideInWeek(CourseGroupOptaPlaner courseGroup) {
        return getDay().compareTo(courseGroup.getDay()) == 0;
    }

    public boolean isAfterInWeek(CourseGroupOptaPlaner courseGroup) {
        return getDay().compareTo(courseGroup.getDay()) > 0;
    }

    public boolean isBeforeInDay(CourseGroupOptaPlaner courseGroup) {
        return getDay().compareTo(courseGroup.getDay()) != 0
                || (getStartTime().plus(getDuration()).isBefore(courseGroup.getStartTime()));
    }

    public boolean isCollide(CourseGroupOptaPlaner courseGroup) {
        if (getDay().compareTo(courseGroup.getDay()) != 0) {
            return false;
        }
        if (!getStartTime().isBefore(courseGroup.getStartTime()) && getStartTime().isBefore(courseGroup.getEndTime())) {
            return true;
        }
        if (!courseGroup.getStartTime().isBefore(getStartTime()) && courseGroup.getStartTime().isBefore(getEndTime())) {
            return true;
        }
        return false;
    }

    public boolean isAfterInDay(CourseGroupOptaPlaner courseGroup) {
        return getDay().compareTo(courseGroup.getDay()) != 0
                || (courseGroup.getStartTime().plus(getDuration()).isAfter(getStartTime()));
    }

    public DayOfWeek getDay() {
        return dateSlot.getDay();
    }

    public LocalTime getStartTime() {
        return dateSlot.getStartTime();
    }

    public LocalTime getEndTime() {
        return dateSlot.getStartTime().plus(getDuration());
    }

    public static CourseGroupOptaPlaner fromInput(CourseGroup courseGroup) {
        Iterator<Professor> professors = courseGroup.getProfessors() != null ? courseGroup.getProfessors().iterator()
                : new HashSet<Professor>().iterator();
        CourseGroupOptaPlaner courseGroupOptaPlaner = new CourseGroupOptaPlaner(courseGroup.hashCode(), courseGroup,
                professors.hasNext() ? professors.next() : null);
        return courseGroupOptaPlaner;
    }

    public static CourseGroupOptaPlaner fromInput(CourseSlot courseSlot) {
        Iterator<Professor> professors = courseSlot.getProfessors() != null ? courseSlot.getProfessors().iterator()
                : new HashSet<Professor>().iterator();
        CourseGroupOptaPlaner courseGroupOptaPlaner = new CourseGroupOptaPlaner(courseSlot.hashCode(),
                courseSlot.getCourseGroup(), professors.hasNext() ? professors.next() : null, courseSlot.getDateSlot(),
                courseSlot.getRoom());
        return courseGroupOptaPlaner;
    }

    public CourseSlot toOutput() {
        return new CourseSlot(courseGroup, null, professor, room, dateSlot);
    }

    public CourseGroup getCouseGroupe() { return this.courseGroup; }

}
