package server.models;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "room")
public class Room implements IInput {

    @EmbeddedId
    private RoomId roomId;
    private Integer capacity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @JsonIgnore
    @OneToMany(mappedBy = "room", targetEntity = Output.class)
    private List<Output> outputs;

    public Room() {
    }

    public Room(String number, Department department, Integer capacity, RoomType type) {
        this.roomId = new RoomId(number, department);
        this.capacity = capacity;
        this.roomType = type;
    }

    public Room(RoomId roomId, Integer capacity, RoomType roomType) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.roomType = roomType;
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public void setRoomId(RoomId roomId) {
        this.roomId = roomId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @JsonIgnore
    public RoomType getRoomType() {
        return this.roomType;
    }

    @JsonIgnore
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @JsonIgnore
    public String getNumber() {
        return roomId.getNumber();
    }

    @JsonIgnore
    public void setNumber(String number) {
        roomId.setNumber(number);
    }

    @JsonIgnore
    public Department getDepartment() {
        return roomId.getDepartment();
    }

    @JsonIgnore
    public void setDepartment(Department department) {
        roomId.setDepartment(department);
    }
}
