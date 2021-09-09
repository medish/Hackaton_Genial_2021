package server.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity

@Table(name = "degree")
public class Degree implements Input {

    @Id
    private String id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "degrees")
    private List<Course> degrees = new ArrayList<>();

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

    public Degree(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setPosts(List<Course> posts) {
        this.degrees = posts;
    }

    public List<Course> getPosts() {
        return degrees;
    }
}
