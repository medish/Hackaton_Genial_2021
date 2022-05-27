package server.models;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "course_slot")
public class CourseSlot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private CourseGroup courseGroup;

    @ManyToOne
    @JsonIgnore
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

    public int getId() {
        return id;
    }

    public CourseGroup getCourseGroup() {
        return courseGroup;
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
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

    public LocalTime getStartTime() {
        return getDateSlot().getStartTime();
    }

    public LocalTime getEndTime() {
        return getStartTime().plus(getDuration());
    }

    @JsonIgnore
    public Duration getDuration() {
        return courseGroup.getDuration();
    }

    @JsonIgnore
    public int getSize() {
        return courseGroup.getSize();
    }

    @JsonIgnore
    public Course getCourse() {
        return courseGroup.getCourse();
    }

    @JsonIgnore
    public RoomType getRoomType() {
        return courseGroup.getRoomType();
    }

    @JsonIgnore
    public Set<Professor> getProfessors() {
        return courseGroup.getProfessors();
    }

    @JsonIgnore
    public void setDuration(Duration duration) {
        courseGroup.setDuration(duration);
    }

    @JsonIgnore
    public void setSize(int size) {
        courseGroup.setSize(size);
    }

    @JsonIgnore
    public void setCourse(Course course) {
        courseGroup.setCourse(course);
    }

    @JsonIgnore
    public void setRoomType(RoomType room_type) {
        courseGroup.setRoomType(room_type);
    }

    @JsonIgnore
    public void setProfessors(Set<Professor> professors) {
        courseGroup.setProfessors(professors);
    }

    @JsonIgnore
    public Degree getDegree() {
        return courseGroup.getDegree();
    }

    @JsonIgnore
    public void setDegree(Degree degrees) {
        courseGroup.setDegree(degrees);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return courseGroup.toString();
    }

}
