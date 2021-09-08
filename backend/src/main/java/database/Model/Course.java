package database.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name = "course")
public class Course {

    @Id
    private int id;

    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "course_degree",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "degree_id")
    )

    private List<Degree> degrees = new ArrayList<>();

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

    public Course(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public List<Degree> getDegrees()
    {
        return degrees;
    }

    public void setDegrees(List<Degree> degrees)
    {
        this.degrees = degrees;
    }
}
