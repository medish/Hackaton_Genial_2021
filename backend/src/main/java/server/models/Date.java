package server.models;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Date")
public class Date {

    @EmbeddedId
    private DateId dateId;

    public Date(DateId dateId) {
        this.dateId = dateId;
    }

    public Date() {
    }

    @OneToMany(mappedBy = "date", targetEntity = Output.class)
    private List<Output> outputs;

    @OneToMany(mappedBy = "date_begin", targetEntity = TimeConstraint.class)
    private List<TimeConstraint> timeConstraintsBegin;

    @OneToMany(mappedBy = "date_end", targetEntity = TimeConstraint.class)
    private List<TimeConstraint> timeConstraintsEnd;

    public DateId getDateId() {
        return dateId;
    }

    public void setDateId(DateId dateId) {
        this.dateId = dateId;
    }

    public LocalTime getHour() {
        return dateId.getHour();
    }

    public void setHour(LocalTime hour) {
        dateId.setHour(hour);
    }

    public DayOfWeek getDay() {
        return dateId.getDay();
    }

    public void setDay(DayOfWeek day) {
        dateId.setDay(day);
    }

    @Override
    public String toString() {
        return dateId.toString();
    }
}
