package server.Model;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

@Table(name = "Lesson")
public class Lesson {

    @Id
    @Column (name="lesson_id")
    private String id;

    private Time duration;
    private int group_size;
    private int course_id;
    private int room_type_id;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "lesson_professor",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professors;

    @OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Output output;

    public Lesson(String id, Time duration, int group_size, int course_id, int room_type_id, List<Professor> professors)
    {
        this.id = id;
        this.duration = duration;
        this.group_size = group_size;
        this.course_id = course_id;
        this.room_type_id = room_type_id;
        this.professors = professors;
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Getter-------------------------------*/
    /*---------------------------------------------------------------*/

    public String getId()
    {
        return id;
    }

    public Date getDuration()
    {
        return duration;
    }

    public int getGroupSize()
    {
        return group_size;
    }

    public int getCourseId()
    {
        return course_id;
    }

    public int getRoomTypeId()
    {
        return room_type_id;
    }

    public List<Professor> getProfessors()
    {
        return professors;
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Setter-------------------------------*/
    /*---------------------------------------------------------------*/

    public void setId(String id)
    {
        this.id = id;
    }

    public void setDuration(Time duration)
    {
        this.duration = duration;
    }

    public void setGroup_size(int group_size)
    {
        this.group_size = group_size;
    }

    public void setCourse_id(int course_id)
    {
        this.course_id = course_id;
    }

    public void setRoom_type_id(int room_type_id)
    {
        this.room_type_id = room_type_id;
    }

    public void setProfessors(List<Professor> professors)
    {
        this.professors = professors;
    }
}
