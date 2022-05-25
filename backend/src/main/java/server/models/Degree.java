package server.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name = "degree")
public class Degree implements IInput {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "degree")
    private Set<Course> courses;

    @ManyToMany(mappedBy = "degrees")
    private Set<Major> majors;

    public Degree() {
    }

    public Degree(int id, String name) {
        this.id = id;
        this.name = name;
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Major> getMajors() {
        return majors;
    }

    public void setMajors(Set<Major> majors) {
        this.majors = majors;
    }
}
