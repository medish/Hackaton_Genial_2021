package server.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RoomType")
public class RoomType implements Input {

    @Id
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "roomType", targetEntity = Room.class)
    private List<Room> rooms;

    @OneToMany(mappedBy = "room_type", targetEntity = Lesson.class)
    private List<Lesson> lessons;

    public RoomType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoomType(String name) {
        this.name = name;
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Getter-------------------------------*/
    /*---------------------------------------------------------------*/

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Setter-------------------------------*/
    /*---------------------------------------------------------------*/

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
