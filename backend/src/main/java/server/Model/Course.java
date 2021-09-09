package server.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

@Table(name = "course")
public class Course {

    @Id
    private int id;

    @Column(unique = true)
    private String name;

    @Column(name = "color")
    private String color;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })

    @JoinTable(name = "course_degree",
            joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "degree_id",referencedColumnName = "id")
    )
    private Set<Degree> degrees = new HashSet<>();

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    public String getColor()
    {
        return this.color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public Course(int id, String name, String color)
    {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Set<Degree> getDegrees()
    {
        return degrees;
    }

    public void setDegrees(Set<Degree> degrees)
    {
        this.degrees = degrees;
    }
}
