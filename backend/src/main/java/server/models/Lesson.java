package server.models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Lesson")
public class Lesson implements Input {

    @Id
    @Column(name = "lesson_id")
    private String id;

    private Time duration;
    private int group_size;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType room_type;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "lesson_professor", joinColumns = @JoinColumn(name = "lesson_id"), inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private Set<Professor> professors;


    public Lesson(String id, Time duration, int group_size, Course course, RoomType room_type, Set<Professor> professors)
    {
        this.id = id;
        this.duration = duration;
        this.group_size = group_size;
        this.course = course;
        this.room_type = room_type;
        this.professors = professors;
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Getter-------------------------------*/
    /*---------------------------------------------------------------*/

    public String getId() {
        return id;
    }

    public Date getDuration() {
        return duration;
    }

    public int getGroupSize() {
        return group_size;
    }

    public Course getCourse() {
        return course;
    }

    public RoomType getRoomType() {
        return room_type;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Setter-------------------------------*/
    /*---------------------------------------------------------------*/

    public void setId(String id) {
        this.id = id;
    }

    public void setDuration(Time duration)
    {
        this.duration = duration;
    }

    public void setGroup_size(int group_size) {
        this.group_size = group_size;
    }

    public void setCourse_id(Course course) {
        this.course = course;
    }

    public void setRoom_type_id(RoomType room_type) {
        this.room_type = room_type;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }
}
