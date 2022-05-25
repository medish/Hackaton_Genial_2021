package server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.awt.*;
import java.util.List;
import java.util.Set;

@Entity

@Table(name = "course")
public class Course implements IInput {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "degree_id", nullable = false)
    private Degree degree;

    @Column(name = "color")
    private Color color;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<CourseGroup> courseGroup;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private Set<Major> majors;

    @ManyToMany(mappedBy = "courses")
    private Set<Professor> professors;

    public Course(int id, String name, Degree degree, Color color) {
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.color = color;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public List<CourseGroup> getCourseGroup() {
        return courseGroup;
    }
    
    public void setCourseGroup(List<CourseGroup> courseGroup) {
        this.courseGroup = courseGroup;
    }
    
    public Set<Major> getMajors() {
        return majors;
    }

    public void setMajors(Set<Major> majors) {
        this.majors = majors;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }
}
