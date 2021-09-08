package core.output;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Timeslot implements Output {

	private DayOfWeek dayOfWeek;
	private LocalTime startTime;
	private LocalTime endTime;

	public Timeslot(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Timeslot(DayOfWeek dayOfWeek, LocalTime startTime) {
		this(dayOfWeek, startTime, startTime.plusMinutes(50));
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

	public LocalTime getEndTime() {
		return endTime;
	}

	public boolean compareTimeSlot(Timeslot timeslot) {
		if (this.dayOfWeek.compareTo(timeslot.getDayOfWeek()) == 0)
			return true;
		else if (this.startTime.compareTo(timeslot.getStartTime()) == 0)
			return true;
		else if (this.endTime.compareTo(timeslot.getEndTime()) == 0)
			return true;

		return false;
	}
}
