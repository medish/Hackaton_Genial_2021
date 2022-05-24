package server.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

@Entity
@Table(name = "course_slot")
public class CourseSlot {

    @Id
    @ManyToOne
    private CourseGroup courseGroup;

    @Id
    @ManyToOne
    private Planning planning;

    @ManyToOne
    private Professor professor;
    @ManyToOne
    private Room room;

    @ManyToOne
    private DateSlot dateSlot;

    public CourseSlot() {
    }

    public CourseSlot(CourseGroup courseGroup, Planning planning, Professor professor, Room room, DateSlot dateSlot) {
        this.courseGroup = courseGroup;
        this.planning = planning;
        this.professor = professor;
        this.room = room;
        this.dateSlot = dateSlot;
    }

    public boolean isBeforeInWeek(CourseSlot courseSlot) {
        return getDay().compareTo(courseSlot.getDay()) < 0;
    }

    public boolean isCollideInWeek(CourseSlot courseSlot) {
        return getDay().compareTo(courseSlot.getDay()) == 0;
    }

    public boolean isAfterInWeek(CourseSlot courseSlot) {
        return getDay().compareTo(courseSlot.getDay()) > 0;
    }

    public boolean isBeforeInDay(CourseSlot courseSlot) {
        return getDay().compareTo(courseSlot.getDay()) != 0
                || (getStartTime().plus(getDuration()).isBefore(courseSlot.getStartTime()));
    }

    public boolean isCollide(CourseSlot courseSlot) {
        if (getDay().compareTo(courseSlot.getDay()) != 0) {
            return false;
        }
        if (!getStartTime().isBefore(courseSlot.getStartTime()) && getStartTime().isBefore(courseSlot.getEndTime())) {
            return true;
        }
        if (!courseSlot.getStartTime().isBefore(getStartTime()) && courseSlot.getStartTime().isBefore(getEndTime())) {
            return true;
        }
        return false;
    }

    public boolean isAfterInDay(CourseSlot courseSlot) {
        return getDay().compareTo(courseSlot.getDay()) != 0
                || (courseSlot.getStartTime().plus(courseSlot.getDuration()).isAfter(getStartTime()));
    }




    public CourseGroup getCourseGroup() {
        return courseGroup;
    }

    public Planning getPlanning() {
        return planning;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public DateSlot getDateSlot() {
        return dateSlot;
    }

    public void setDateSlot(DateSlot dateSlot) {
        this.dateSlot = dateSlot;
    }

    private DayOfWeek getDay() {
        return getDateSlot().getDay();
    }

    private LocalTime getStartTime(){
        return getDateSlot().getStartTime();
    }

    public LocalTime getEndTime() {
        return getStartTime().plus(getDuration());
    }

    public Duration getDuration() {
        return courseGroup.getDuration();
    }
}
