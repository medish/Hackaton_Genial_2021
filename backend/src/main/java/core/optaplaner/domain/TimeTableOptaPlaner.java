package core.optaplaner.domain;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import core.dataclasses.Room;
import core.output.TimeTable;
import core.output.Timeslot;

@PlanningSolution
public class TimeTableOptaPlaner implements FromOptaplanerToOutput<TimeTable>, FromInputToOptaPlaner<TimeTable> {

	@ProblemFactCollectionProperty
	@ValueRangeProvider(id = "timeslotRange")
	private List<Timeslot> timeslotList;
	@ProblemFactCollectionProperty
	@ValueRangeProvider(id = "roomRange")
	private List<Room> roomList;
	@PlanningEntityCollectionProperty
	private List<LessonOptaPlaner> lessonList;

	@PlanningScore
	private HardSoftScore score;

	public TimeTableOptaPlaner() {
	}

	public TimeTableOptaPlaner(List<Timeslot> timeslotList, List<Room> roomList, List<LessonOptaPlaner> lessonList) {
		this.timeslotList = timeslotList;
		this.roomList = roomList;
		this.lessonList = lessonList;
	}

	public List<Timeslot> getTimeslotList() {
		return timeslotList;
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public List<LessonOptaPlaner> getLessonList() {
		return lessonList;
	}

	public HardSoftScore getScore() {
		return score;
	}

	@Override
	public TimeTable toOutput() {
		TimeTable timetable = new TimeTable(timeslotList, roomList, lessonList);
		return timetable;
	}

	public static TimeTableOptaPlaner fromInput(TimeTable timeTable) {
		TimeTableOptaPlaner timeTableOptaPlaner = new TimeTableOptaPlaner(timeTable.getTimeslotList(),
				timeTable.getRoomList(), timeTable.getLessonList());
		return timeTableOptaPlaner;
	}
}
