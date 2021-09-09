package server.models;

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

    @ManyToOne
    @MapsId
    @JoinColumn(name="lesson_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "planning_id", nullable = false)
    private Planning planning;

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

    public int getHour_begin()
    {
        return hour_begin;
    }

    public String getId()
    {
        return id;
    }

    public void setHour_begin(int hour_begin)
    {
        this.hour_begin = hour_begin;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
