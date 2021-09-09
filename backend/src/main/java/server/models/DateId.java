package server.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Embeddable
public class DateId implements Serializable {

    @OneToOne
    @JoinColumn(name = "day_id", nullable = false)
    private Day day;

    @Column(name = "hour", nullable = false)
    private Time hour;

    public DateId(Time hour, Day day) {
        this.hour = hour;
        this.day = day;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
