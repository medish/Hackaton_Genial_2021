package server.models;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "date_slot")
@IdClass(DateSlotId.class)
public class DateSlot implements IInput, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private DayOfWeek day;

    @Id
    private LocalTime startTime;

    public DateSlot(DayOfWeek day, LocalTime startTime) {
        this.day = day;
        this.startTime = startTime;
    }

    public DateSlot() {
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
