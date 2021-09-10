package server.models;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name = "department")
public class Department implements IInput {

    @Id
    private String id;

    @Column(unique = true, name = "name", nullable = false)
    private String name;

    @ElementCollection(targetClass = RoomId.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "RoomID", joinColumns = @JoinColumn(name = "department"))
    @Column(name = "Room")
    private List<Room> rooms;


    public Department(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department() {
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
