package core.optaplaner.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import core.output.TimeTable;
import server.models.DateSlot;
import server.models.Room;

@PlanningSolution
public class TimeTableOptaPlaner implements FromOptaplanerToOutput<TimeTable>, FromInputToOptaPlaner<TimeTable> {

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "timeslotRange")
    private List<DateSlot> dateSlots;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "roomRange")
    private List<Room> rooms;

    @PlanningEntityCollectionProperty
    private List<CourseGroupOptaPlaner> courseGroups;

    @PlanningScore
    private HardSoftScore score;

    public TimeTableOptaPlaner() {
    }

    public TimeTableOptaPlaner(List<DateSlot> dateSlots, List<Room> rooms, List<CourseGroupOptaPlaner> courseGroups) {
        this.dateSlots = dateSlots;
        this.rooms = rooms;
        this.courseGroups = courseGroups;
    }

    public List<DateSlot> getDateSlots() {
        return dateSlots;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<CourseGroupOptaPlaner> getCourseGroups() {
        return courseGroups;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    @Override
    public TimeTable toOutput() {
        TimeTable timetable = new TimeTable(dateSlots, rooms,
                courseGroups.stream().map(CourseGroupOptaPlaner::toOutput).collect(Collectors.toList()));
        return timetable;
    }

    public static TimeTableOptaPlaner fromInput(TimeTable timeTable) {
        TimeTableOptaPlaner timeTableOptaPlaner = new TimeTableOptaPlaner(timeTable.getDateSlots(),
                timeTable.getRooms(), timeTable.getCourseSlots().stream()
                        .map((output) -> CourseGroupOptaPlaner.fromInput(output)).collect(Collectors.toList()));
        return timeTableOptaPlaner;
    }
}
