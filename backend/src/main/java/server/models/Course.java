package server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity

@Table(name = "course")
public class Course implements IInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "degree_id", nullable = false)
    private Degree degree;

    @Column(name = "color", length = 6)
    private String color;

    @ManyToMany(mappedBy = "courses")
    private Set<Professor> professors;


    @OneToMany(mappedBy = "course")
    private Set<MajorCourse> majorCourses;
    public Course() {
    }

    public Course(String name, Degree degree, String color) {
        this.name = name;
        this.degree = degree;
        this.color = color;
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

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    @JsonIgnore
    public Set<MajorCourse> getMajorCourses() {
        return majorCourses;
    }

    public void setMajorCourses(Set<MajorCourse> majorCourses) {
        this.majorCourses = majorCourses;
    }
}
