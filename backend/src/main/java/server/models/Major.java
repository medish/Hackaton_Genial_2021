package server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity

@Table(name = "major")
public class Major implements IInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "major_degree", joinColumns = @JoinColumn(name = "major_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "degree_id", referencedColumnName = "id"))
    private Set<Degree> degrees;

    @OneToMany(mappedBy = "major")
    private Set<MajorCourse> majorCourses;

    public Major() {
    }

    public Major(String name) {
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

    public Set<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(Set<Degree> degrees) {
        this.degrees = degrees;
    }

    @JsonIgnore
    public Set<MajorCourse> getMajorCourses() {
        return majorCourses;
    }

    public void setMajorCourses(Set<MajorCourse> majorCourses) {
        this.majorCourses = majorCourses;
    }
}
