package server.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.optaplanner.core.api.score.ScoreExplanation;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.SolverTimeTable;
import core.optaplaner.SolverOptaplaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.output.TimeTable;
import server.models.CourseSlot;
import server.models.DateSlot;
import server.models.Planning;
import server.models.PrecedenceConstraint;
import server.models.Room;
import server.models.TimeConstraint;

@Service
public class PlanningSolver {
    @Autowired
    private PlanningService planningService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private CourseSlotService courseSlotService;
    @Autowired
    private DateSlotService dateSlotService;
    @Autowired
    private CourseGroupService courseGroupService;
    @Autowired
    private TimeConstraintService timeConstraintService;
    @Autowired
    private PrecedenceConstraintService precedenceConstraintService;

    private static final SolverTimeTable solver = new SolverOptaplaner();

    /**
     * Solves planning
     * 
     * @return {@link Planning}
     */
    public Planning solve(int id) {
        List<DateSlot> dateList = dateSlotService.getAll();
        // Constraints Time + precedence
        List<TimeConstraint> timeConstraints = timeConstraintService.getAll();
        List<PrecedenceConstraint> precedenceConstraints = precedenceConstraintService.getAll();
        // Lessons
        List<CourseSlot> outputList = TimeTable.buildListSlots(courseGroupService.getAll());
        // Rooms
        List<Room> roomList = roomService.getAll();

        TimeTable timeTable = new TimeTable(dateList, roomList, outputList);

        List<CourseSlot> resolvedOutput = solver.solve(timeTable, timeConstraints, precedenceConstraints)
                .getCourseSlots();

        Planning planning = new Planning(id, "name", LocalDate.now());
        planningService.insert(planning);

        planning.setSlots(new HashSet<>(resolvedOutput));
        for (CourseSlot courseSlot : resolvedOutput) {
            courseSlot.setPlanning(planning);
        }
        courseSlotService.delete(courseSlotService.getAll().stream().map(CourseSlot::getId).toList());
        courseSlotService.insert(resolvedOutput);
        return planning;
    }

    /**
     * Checks a planning for a given output list.
     * 
     * @param planning
     * @return {@link ScoreExplanation}
     */
    public ScoreExplanation<TimeTableOptaPlaner, HardSoftScore> checkPlanning(Planning planning) {
        // Constraints Time + precedence
        List<TimeConstraint> timeConstraints = timeConstraintService.getAll();
        List<PrecedenceConstraint> precedenceConstraints = precedenceConstraintService.getAll();
        // Rooms
        List<Room> roomList = roomService.getAll();

        List<DateSlot> dateList = dateSlotService.getAll();

        TimeTable timeTable = new TimeTable(dateList, roomList, new ArrayList<>(planning.getSlots()));

        return solver.verify(timeTable, timeConstraints, precedenceConstraints);
    }
}
