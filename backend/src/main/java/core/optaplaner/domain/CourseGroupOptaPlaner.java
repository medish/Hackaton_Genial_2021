package core.optaplaner.domain;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import server.models.Course;
import server.models.CourseGroup;
import server.models.CourseSlot;
import server.models.DateSlot;
import server.models.Professor;
import server.models.Room;
import server.models.RoomType;

@PlanningEntity
public class CourseGroupOptaPlaner extends AbstractPersistable implements FromInputToOptaPlaner<CourseGroup> {

    private Course course;
    private Professor professor;
    private Duration duration;
    private RoomType roomType;

    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private DateSlot dateSlot;
    @PlanningVariable(valueRangeProviderRefs = "roomRange")
    private Room room;

    public CourseGroupOptaPlaner() {
    }

    public CourseGroupOptaPlaner(long id, Course course, Professor professor, Duration duration, RoomType roomType) {
        super(id);
        this.course = course;
        this.professor = professor;
        this.duration = duration;
        this.roomType = roomType;
    }

    public CourseGroupOptaPlaner(long id, Course course, Professor professor, Duration duration, RoomType roomType,
            DateSlot dateSlot, Room room) {
        this(id, course, professor, duration, roomType);
        this.dateSlot = dateSlot;
        this.room = room;
    }

    @Override
    public String toString() {
        return course + "(" + id + ")";
    }

    public Course getCourse() {
        return course;
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
                || (getStartTime().plus(duration).isBefore(courseGroup.getStartTime()));
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
                || (courseGroup.getStartTime().plus(duration).isAfter(getStartTime()));
    }

    public DayOfWeek getDay() {
        return dateSlot.getDay();
    }

    public LocalTime getStartTime() {
        return dateSlot.getStartTime();
    }

    public LocalTime getEndTime() {
        return dateSlot.getStartTime().plus(duration);
    }

    public static CourseGroupOptaPlaner fromInput(CourseGroup courseGroup) {
        Iterator<Professor> professors = courseGroup.getProfessors() != null ? courseGroup.getProfessors().iterator()
                : new HashSet<Professor>().iterator();
        CourseGroupOptaPlaner courseGroupOptaPlaner = new CourseGroupOptaPlaner(courseGroup.hashCode(),
                courseGroup.getCourse(), professors.hasNext() ? professors.next() : null, courseGroup.getDuration(),
                courseGroup.getRoomType());
        return courseGroupOptaPlaner;
    }

    public static CourseGroupOptaPlaner fromInput(CourseSlot courseSlot) {
        Iterator<Professor> professors = courseSlot.getProfessors() != null ? courseSlot.getProfessors().iterator()
                : new HashSet<Professor>().iterator();
        CourseGroupOptaPlaner courseGroupOptaPlaner = new CourseGroupOptaPlaner(courseSlot.hashCode(),
                courseSlot.getCourse(), professors.hasNext() ? professors.next() : null, courseSlot.getDuration(),
                courseSlot.getRoomType(), courseSlot.getDateSlot(), courseSlot.getRoom());
        return courseGroupOptaPlaner;
    }

    public CourseSlot toOutput() {
        return new CourseSlot(new CourseGroup(0, course, duration, 0, roomType), null, professor, room, dateSlot);
    }
}
