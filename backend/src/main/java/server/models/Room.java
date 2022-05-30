package server.models;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import server.services.KeyID;

@Entity
@Table(name = "room")
public class Room implements IInput, KeyID<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Room(int id) {
        this.id = id;
    }

    public Room(int id, String name, Department department, int capacity, Set<RoomType> roomTypes) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.department = department;
        this.roomTypes = roomTypes;
    }

    @Override
    public Integer getId() {
        return id;
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

    public Set<RoomType> getRoomTypes() {
        return this.roomTypes;
    }
}
