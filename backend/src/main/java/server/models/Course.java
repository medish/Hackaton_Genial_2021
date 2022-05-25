package server.models;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<CourseGroup> courseGroup;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private Set<Major> majors;

    @ManyToMany(mappedBy = "courses")
    private Set<Professor> professors;

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
