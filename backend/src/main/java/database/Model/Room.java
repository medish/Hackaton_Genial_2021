package database.Model;

import javax.persistence.*;

@Entity

@Table(name = "room")
public class Room {

    @EmbeddedId
    private RoomId roomId;
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    public Room(Integer capacity, RoomType type)
    {
        this.capacity = capacity;
        this.roomType = type;
    }

    public Integer getCapacity()
    {
        return capacity;
    }

    public void setCapacity(Integer capacity)
    {
        this.capacity = capacity;
    }

    public void setRoomId(RoomId roomId)
    {
        this.roomId = roomId;
    }

    public RoomId getRoomId()
    {
        return roomId;
    }
}
