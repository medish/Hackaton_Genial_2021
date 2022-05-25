package server.models;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class DateSlotId implements Serializable {
    private static final long serialVersionUID = 1L;

    private DayOfWeek day;
    private LocalTime startTime;

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
