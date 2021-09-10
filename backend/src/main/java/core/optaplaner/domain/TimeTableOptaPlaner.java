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
import server.models.Date;
import server.models.Room;

@PlanningSolution
public class TimeTableOptaPlaner implements FromOptaplanerToOutput<TimeTable>, FromInputToOptaPlaner<TimeTable> {

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "timeslotRange")
    private List<Date> timeslotList;
    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "roomRange")
    private List<Room> roomList;
    @PlanningEntityCollectionProperty
    private List<LessonOptaPlaner> lessonList;

    @PlanningScore
    private HardSoftScore score;

    public TimeTableOptaPlaner() {
    }

    public TimeTableOptaPlaner(List<Date> timeslotList, List<Room> roomList, List<LessonOptaPlaner> lessonList) {
        this.timeslotList = timeslotList;
        this.roomList = roomList;
        this.lessonList = lessonList;
    }

    public List<Date> getDateList() {
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

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    @Override
    public TimeTable toOutput() {
        TimeTable timetable = new TimeTable(timeslotList, roomList,
                lessonList.stream().map(LessonOptaPlaner::toOutput).collect(Collectors.toList()));
        return timetable;
    }

    public static TimeTableOptaPlaner fromInput(TimeTable timeTable) {
        TimeTableOptaPlaner timeTableOptaPlaner = new TimeTableOptaPlaner(timeTable.getDateList(),
                timeTable.getRoomList(), timeTable.getOutputList().stream()
                        .map((output) -> LessonOptaPlaner.fromInput(output)).collect(Collectors.toList()));
        return timeTableOptaPlaner;
    }
}
