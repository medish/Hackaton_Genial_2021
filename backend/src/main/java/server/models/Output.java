package server.models;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Output")
public class Output {

    public Output() {
    }

    @Id
    @Column(name = "lesson_id")
    private String id;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "output_day", nullable = false, referencedColumnName = "day"),
            @JoinColumn(name = "output_hour", nullable = false, referencedColumnName = "hour") })
    private Date date;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "output_room_id", nullable = false, referencedColumnName = "id"),
            @JoinColumn(name = "output_room_department", nullable = false, referencedColumnName = "room_department_id") })
    private Room room;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "planning_id", nullable = false)
    private Planning planning;

    public Output(Date date, Room room, Lesson lesson) {
        this.date = date;
        this.room = room;
        this.lesson = lesson;
    }

    public boolean isBeforeInWeek(Output output) {
        return getDay().compareTo(output.getDay()) < 0;
    }

    public boolean isCollideInWeek(Output output) {
        return getDay().compareTo(output.getDay()) == 0;
    }

    public boolean isAfterInWeek(Output output) {
        return getDay().compareTo(output.getDay()) > 0;
    }

    public boolean isBeforeInDay(Output output) {
        return getDay().compareTo(output.getDay()) != 0
                || (getStartTime().plus(getDuration()).isBefore(output.getStartTime()));
    }

    public boolean isCollide(Output output) {
        if (getDay().compareTo(output.getDay()) != 0) {
            return false;
        }
        if (!getStartTime().isBefore(output.getStartTime()) && getStartTime().isBefore(output.getEndTime())) {
            return true;
        }
        if (!output.getStartTime().isBefore(getStartTime()) && output.getStartTime().isBefore(getEndTime())) {
            return true;
        }
        return false;
    }

    public boolean isAfterInDay(Output output) {
        return getDay().compareTo(output.getDay()) != 0
                || (output.getStartTime().plus(output.getDuration()).isAfter(getStartTime()));
    }

    public DayOfWeek getDay() {
        return date.getDay();
    }

    public LocalTime getStartTime() {
        return date.getHour();
    }

    public LocalTime getEndTime() {
        return date.getHour().plus(getDuration());
    }

    public Date getDate() {
        return date;
    }

    public Room getRoom() {
        return room;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public String getId() {
        return lesson.getId();
    }

    public void setId(String id) {
        lesson.setId(id);
    }

    public Duration getDuration() {
        return lesson.getDuration();
    }

    public int getGroupSize() {
        return lesson.getGroupSize();
    }

    public Course getCourse() {
        return lesson.getCourse();
    }

    public RoomType getRoomType() {
        return lesson.getRoomType();
    }

    public Set<Professor> getProfessors() {
        return lesson.getProfessors();
    }

    public void setDuration(Duration duration) {
        lesson.setDuration(duration);
    }

    public void setGroup_size(int group_size) {
        lesson.setGroup_size(group_size);
    }

    public void setCourse_id(Course course) {
        lesson.setCourse_id(course);
    }

    public void setRoom_type_id(RoomType room_type) {
        lesson.setRoom_type_id(room_type);
    }

    public void setProfessors(Set<Professor> professors) {
        lesson.setProfessors(professors);
    }

    @Override
    public String toString() {
        return lesson.toString();
    }

    public Set<Degree> getDegrees() {
        return lesson.getDegrees();
    }

    public void setDegrees(Set<Degree> degrees) {
        lesson.setDegrees(degrees);
    }

}
