package server.models;

import java.awt.Color;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name = "course")
public class Course implements Input {

    @Id
    private int id;

    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "course_degree", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "degree_id", referencedColumnName = "id"))
    private Set<Degree> degrees = new HashSet<>();

    @OneToMany(mappedBy = "course", targetEntity = Lesson.class)
    private List<Lesson> lessons;

    @Column(name = "color")
    private Color color;

    public Course(int id, String name, Set<Degree> degrees, Color color) {
        this.id = id;
        this.name = name;
        this.degrees = degrees;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(Set<Degree> degrees) {
        this.degrees = degrees;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
