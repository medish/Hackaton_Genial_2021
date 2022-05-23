package server.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

@Table(name = "degree")
public class Degree implements IInput {

    @Id
    private String id;

    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "degrees")
    private Set<Course> courses = new HashSet<>();

    public Degree() {
    }

    public Degree(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @JsonIgnore
    public Set<Course> getCourses() {
        return courses;
    }
}
