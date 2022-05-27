package server.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "major_course")
@IdClass(MajorCourseId.class)
public class MajorCourse implements IInput, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    private Course course;

    @Id
    @ManyToOne
    private Major major;

    @OneToMany(mappedBy = "majorCourse")
    private Set<CourseGroup> courseGroups;

    public MajorCourse(Course course, Major major) {
        this.course = course;
        this.major = major;
    }

    public MajorCourse(){}

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
}
