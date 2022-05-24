package server.models;

import javax.persistence.*;
import java.util.List;
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

    @OneToMany(mappedBy = "room")
    private Set<CourseSlot> slots;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = RoomType.class)
    List<RoomType> roomTypes;
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
