package server.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Planning")
public class Planning {

    @Id
    private String id;

    private String name;
    private Date createdAt;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Output> outputs = new ArrayList<>();

    public Planning(String id, Output output, Date createdAt)
    {
        this.id = id;
        this.createdAt = createdAt;
    }

    public Planning(String id)
    {
        this.id = id;
    }

    public Planning(String id, Output output)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Output> getOutputs()
    {
        return outputs;
    }
}
