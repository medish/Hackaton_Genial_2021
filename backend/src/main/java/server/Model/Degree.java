package server.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

@Table(name = "degree")
public class Degree {

    @Id
    private String id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "degrees")
    private Set<Course> courses = new HashSet<>();

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Degree(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public void setCourses(Set<Course> courses)
    {
        this.courses = courses;
    }

    public Set<Course> getCourses()
    {
        return courses;
    }
}
