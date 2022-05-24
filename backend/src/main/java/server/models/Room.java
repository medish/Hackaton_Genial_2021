package server.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room implements IInput {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int capacity;

    @ManyToOne
    private Department department;

    @OneToMany
    private Set<CourseSlot> slots;

    public Room() {
    }

    public Room(String number, int capacity, Department department) {
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

    public int getId() {
        return id;
    }
}
