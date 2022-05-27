package server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Duration;
import java.util.Set;

@Entity
@Table(name = "course_group")
public class CourseGroup implements IInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Course course;

    private Duration duration;

    private int size;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    public CourseGroup() {
    }

    public CourseGroup(int id, Course course, Duration duration, int size, RoomType roomType) {
        this.id = id;
        this.course = course;
        this.duration = duration;
        this.size = size;
        this.roomType = roomType;
    }

    public int getId() {
        return id;
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
