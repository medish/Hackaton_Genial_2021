package server.models;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import core.output.Output;

@Embeddable
public class DateId implements Serializable, Output, Input {

    private static final long serialVersionUID = 1L;

    @Column(name = "day", nullable = false)
    private DayOfWeek day;

    @Column(name = "hour", nullable = false)
    private LocalTime hour;

    public DateId(DayOfWeek day, LocalTime hour) {
        this.hour = hour;
        this.day = day;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return day + " " + hour;
    }
}
