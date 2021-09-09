package core.output;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Timeslot implements Output {

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;

    public Timeslot(DayOfWeek dayOfWeek, LocalTime startTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return dayOfWeek + " " + startTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public boolean compareTimeSlot(Timeslot timeslot) {
        if (this.dayOfWeek.compareTo(timeslot.getDayOfWeek()) == 0)
            return true;
        else if (this.startTime.compareTo(timeslot.getStartTime()) == 0)
            return true;
        return false;
    }
}
