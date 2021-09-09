package server.Model;

import javax.persistence.*;
import java.util.List;

@Entity

@Table(name = "day")
public class Day {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    /*@OneToMany(mappedBy = "day", targetEntity = Date.class)
    private List<Date> dates;*/

    public Day(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

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
}
