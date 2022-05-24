package server.models;

import javax.persistence.*;
import java.awt.*;
import java.util.Set;

@Entity

@Table(name = "course")
public class Course implements IInput {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String name;

    @Column(name = "color")
    private Color color;

    @ManyToOne
    @JoinColumn(name="degree_id", nullable=false)
    private Degree degree;

    @ManyToMany(mappedBy = "courses")
    private Set<Major> majors;

    @ManyToMany(mappedBy = "courses")
    private Set<Professor> professors;

    public Course(int id, String name, Color color, Degree degree) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.degree = degree;
    }

    public Course()
    {

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
