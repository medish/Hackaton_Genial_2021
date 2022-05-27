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
    private MajorCourse majorCourse;

    private Duration duration;

    private int size;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    public CourseGroup() {
    }

    public CourseGroup(int id, MajorCourse majorCourse, Duration duration, int size, RoomType roomType) {
        this.id = id;
        this.majorCourse = majorCourse;
        this.duration = duration;
        this.size = size;
        this.roomType = roomType;
    }

    public int getId() {
        return id;
    }

    public MajorCourse getMajorCourse() {
        return majorCourse;
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

    public void setMajorCourse(MajorCourse majorCourse) {
        this.majorCourse = majorCourse;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @JsonIgnore
    public Set<Professor> getProfessors() {
        return majorCourse.getCourse().getProfessors();
    }

    public void setProfessors(Set<Professor> professors) {
        majorCourse.getCourse().setProfessors(professors);
    }

    @JsonIgnore
    public Degree getDegree() {
        return majorCourse.getCourse().getDegree();
    }

    public void setDegree(Degree degree) {
        majorCourse.getCourse().setDegree(degree);
    }
}
