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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Output")
public class Output {

    public Output() {
    }

    @JsonIgnore
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

    @JsonIgnore
    public DayOfWeek getDay() {
        return date.getDay();
    }

    @JsonIgnore
    public LocalTime getStartTime() {
        return date.getHour();
    }

    @JsonIgnore
    public LocalTime getEndTime() {
        return date.getHour().plus(getDuration());
    }

    @JsonIgnore
    public String getId() {
        return lesson.getId();
    }

    @JsonIgnore
    public void setId(String id) {
        lesson.setId(id);
    }

    @JsonIgnore
    public Duration getDuration() {
        return lesson.getDuration();
    }

    @JsonIgnore
    public int getGroupSize() {
        return lesson.getGroupSize();
    }

    @JsonIgnore
    public Course getCourse() {
        return lesson.getCourse();
    }

    @JsonIgnore
    public RoomType getRoomType() {
        return lesson.getRoomType();
    }

    @JsonIgnore
    public Set<Professor> getProfessors() {
        return lesson.getProfessors();
    }

    @JsonIgnore
    public void setDuration(Duration duration) {
        lesson.setDuration(duration);
    }

    @JsonIgnore
    public void setGroupSize(int group_size) {
        lesson.setGroupSize(group_size);
    }

    @JsonIgnore
    public void setCourse(Course course) {
        lesson.setCourse(course);
    }

    @JsonIgnore
    public void setRoomType(RoomType room_type) {
        lesson.setRoomType(room_type);
    }

    @JsonIgnore
    public void setProfessors(Set<Professor> professors) {
        lesson.setProfessors(professors);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return lesson.toString();
    }

    @JsonIgnore
    public Set<Degree> getDegrees() {
        return lesson.getDegrees();
    }

    @JsonIgnore
    public void setDegrees(Set<Degree> degrees) {
        lesson.setDegrees(degrees);
    }

}
