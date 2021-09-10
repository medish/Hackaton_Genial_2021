package server.models;

import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "TimeConstraint")
public class TimeConstraint implements IInput {

    @Id
    @GeneratedValue
    private String id;

    private String selector;
    private boolean wants;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "date_day", nullable = false, referencedColumnName = "day"),
            @JoinColumn(name = "date_hour", nullable = false, referencedColumnName = "hour") })
    private Date date;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "department_id", nullable = false),
            @JoinColumn(name = "room_id", nullable = false) })
    private Room room;

    private int priority;

    private LocalTime start_time;
    private LocalTime end_time;

    public LocalTime getStart_time() {
        return this.start_time;
    }

    public LocalTime getEnd_time() {
        return this.end_time;
    }

    public TimeConstraint() {

    }

    public TimeConstraint(String id, String selector, boolean wants, Date date, Room room, int priority) {
        this.id = id;
        this.selector = selector;
        this.wants = wants;
        this.date = date;
        this.room = room;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public boolean isWants() {
        return wants;
    }

    public void setWants(boolean wants) {
        this.wants = wants;
    }

    public Date getDateId() {
        return date;
    }

    public void setDateId(Date date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Boolean getWants() {
        return this.wants;
    }


}
