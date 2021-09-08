package database.Model;

import javax.persistence.*;
import java.util.List;

@Entity

@Table(name = "department")
public class Department {

    @Id
    private String id;

    @Column(unique = true, name = "name", nullable = false)
    private String name;

    //@OneToMany(mappedBy = "department", targetEntity = RoomId.class)
    @ElementCollection(targetClass = RoomId.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "RoomID", joinColumns = @JoinColumn(name="department"))
    @Column(name = "Room")
    private List<Room> rooms;

    public Department(String id, String name)
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

    public List<Room> getRooms()
    {
        return rooms;
    }

    public void setRooms(List<Room> rooms)
    {
        this.rooms = rooms;
    }
}