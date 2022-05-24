package server.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "date_slot")
public class DateSlot {

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
