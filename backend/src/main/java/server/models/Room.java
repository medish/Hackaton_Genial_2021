package server.models;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room implements Input {

    @EmbeddedId
    private RoomId roomId;
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @OneToMany(mappedBy = "room", targetEntity = Output.class)
    private List<Output> outputs;

    public Room(String number, Department department, Integer capacity, RoomType type) {
        this.roomId = new RoomId(number, department);
        this.capacity = capacity;
        this.roomType = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getNumber() {
        return roomId.getNumber();
    }

    public void setNumber(String number) {
        roomId.setNumber(number);
    }

    public Department getDepartment() {
        return roomId.getDepartment();
    }

    public void setDepartment(Department department) {
        roomId.setDepartment(department);
    }
}
