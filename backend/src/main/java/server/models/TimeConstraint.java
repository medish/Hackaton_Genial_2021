package server.models;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "time_constraint")
public class TimeConstraint implements IInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String selector;
    private boolean wants;

    @ManyToOne
    private Professor creator;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "day_begin"), @JoinColumn(name = "time_begin") })
    private DateSlot dateBegin;

    @ManyToOne()
    @JoinColumns({ @JoinColumn(name = "day_end"), @JoinColumn(name = "time_end") })
    private DateSlot dateEnd;

    @ManyToOne
    private Room room;

    private int priority;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    public TimeConstraint() {
    }

    public TimeConstraint(String selector, boolean wants, DateSlot dateBegin, DateSlot dateEnd, Room room,
            int priority) {
        this.selector = selector;
        this.wants = wants;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.room = room;
        this.priority = priority;
    }

    public int getId() {
        return id;
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

    public Professor getCreator() {
        return creator;
    }

    public void setCreator(Professor creator) {
        this.creator = creator;
    }

    public DateSlot getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(DateSlot dateBegin) {
        this.dateBegin = dateBegin;
    }

    public DateSlot getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(DateSlot dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

}
