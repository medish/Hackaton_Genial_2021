package server.Model;

import javax.persistence.*;

@Entity
@Table(name = "Output")
public class Output {

    @Id
    @Column (name = "lesson_id")
    private String id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "output_day_id", nullable = false, referencedColumnName =  "day_id"),
            @JoinColumn(name = "output_hour", nullable = false, referencedColumnName =  "hour")
    })
    private Date date;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "output_room_id", nullable = false, referencedColumnName =  "id"),
            @JoinColumn(name = "output_room_department", nullable = false, referencedColumnName =  "room_department_id")
    })
    private Room room;

    @OneToOne
    @MapsId
    @JoinColumn(name="lesson_id")
    private Lesson lesson;

    public Output(Date date, Room room, Lesson lesson)
    {
        this.date = date;
        this.room = room;
        this.lesson = lesson;
    }

    public Date getDate()
    {
        return date;
    }

    public Room getRoom()
    {
        return room;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }

    public void setLesson(Lesson lesson)
    {
        this.lesson = lesson;
    }

    public Lesson getLesson()
    {
        return lesson;
    }
}
