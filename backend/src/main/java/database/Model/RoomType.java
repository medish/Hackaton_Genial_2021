package database.Model;

import javax.persistence.*;
import java.util.List;

@Entity

@Table(name = "RoomType")
public class RoomType {

    @Id
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "roomType", targetEntity = Room.class)
    private List<Room> rooms;

    public RoomType(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public RoomType(String name)
    {
        this.name = name;
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Getter-------------------------------*/
    /*---------------------------------------------------------------*/

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    /*---------------------------------------------------------------*/
    /*-------------------------Setter-------------------------------*/
    /*---------------------------------------------------------------*/

    public void setName(String name)
    {
        this.name = name;
    }

    public void setId(int id)
    {
        this.id = id;
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
