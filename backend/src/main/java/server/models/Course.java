package server.models;

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

import server.services.KeyID;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity

@Table(name = "course")
public class Course implements IInput, KeyID<Integer> {

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

    public Course(int id, String name, Degree degree, String color) {
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.color = color;
    }

    @Override
    public Integer getId() {
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
