package server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.util.Set;

@Entity
@Table(name = "course_group")
@IdClass(CourseGroupId.class)
public class CourseGroup implements IInput, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "group_id", nullable = false)
    private int groupId;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private Duration duration;

    private int size;

    @Enumerated(EnumType.ORDINAL)
    private RoomType roomType;

    public CourseGroup() {
    }

    public CourseGroup(int groupId, Course course, Duration duration, int size) {
        this.groupId = groupId;
        this.course = course;
        this.duration = duration;
        this.size = size;
    }

    public int getGroupId() {
        return groupId;
    }

    public Course getCourse() {
        return course;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @JsonIgnore
    public Set<Professor> getProfessors() {
        return course.getProfessors();
    }

    public void setProfessors(Set<Professor> professors) {
        course.setProfessors(professors);
    }

    @JsonIgnore
    public Degree getDegree() {
        return course.getDegree();
    }

    public void setDegree(Degree degree) {
        course.setDegree(degree);
    }
}
