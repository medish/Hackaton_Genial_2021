package server.models;

import javax.persistence.*;
import java.util.Set;

@Entity

@Table(name = "department")
public class Department implements IInput {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true, name = "name", nullable = false)
    private String name;

    @OneToMany
    private Set<Room> rooms;
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department() {
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

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
