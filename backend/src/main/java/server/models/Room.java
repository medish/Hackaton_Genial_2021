package server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room implements IInput {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private int capacity;

    @JsonIgnore
    @ManyToOne
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Set<CourseSlot> slots;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = RoomType.class)
    Set<RoomType> roomTypes;

    public Room() {
    }

    public Room(int id, String name, Department department, int capacity, Set<RoomType> roomTypes) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.department = department;
        this.roomTypes = roomTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<CourseSlot> getSlots() {
        return slots;
    }

    public void setSlots(Set<CourseSlot> slots) {
        this.slots = slots;
    }
}
