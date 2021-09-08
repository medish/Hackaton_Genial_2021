package server.Model;

import javax.persistence.*;

@Entity
@Table(name = "Output")
public class Output {

    private int hour_begin ;

    @Id
    @Column (name = "lesson_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "output_day_id", nullable = false)
    private Day day;

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

    public Output(int hour_begin, Day day, Room room, Lesson lesson)
    {
        this.hour_begin = hour_begin;
        this.day = day;
        this.room = room;
        this.lesson = lesson;
    }

    public int getHourBegin()
    {
        return hour_begin;
    }

    public Day getDay()
    {
        return day;
    }

    public Room getRoom()
    {
        return room;
    }

    public void setHourBegin(int hour_begin)
    {
        this.hour_begin = hour_begin;
    }

    public void setDay(Day day)
    {
        this.day = day;
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
