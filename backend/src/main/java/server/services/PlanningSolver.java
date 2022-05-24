package server.services;

import core.SolverTimeTable;
import core.optaplaner.SolverOptaplaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.output.TimeTable;
import core.planning.DateList;
import org.optaplanner.core.api.score.ScoreExplanation;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PlanningSolver {
    @Autowired
    private PlanningService planningService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private CourseGroupService courseGroupService;
    @Autowired
    private TimeConstraintService timeConstraintService;
    @Autowired
    private PrecedenceConstraintService precedenceConstraintService;

    private static final List<DateSlot> dateList = DateList.build();
    private static final SolverTimeTable solver = new SolverOptaplaner();

    /**
     * Solves planning
     * 
     * @return {@link Planning}
     */
    public Planning solve() {
        // Constraints Time + precedence
        List<TimeConstraint> timeConstraints = timeConstraintService.getAll();
        List<PrecedenceConstraint> precedenceConstraints = precedenceConstraintService.getAll();
        // Lessons
        List<CourseSlot> outputList = TimeTable.buildListSlots(courseGroupService.getAll());
        // Rooms
        List<Room> roomList = roomService.getAll();

        TimeTable timeTable = new TimeTable(dateList, roomList, outputList);

        List<CourseSlot> resolvedOutput = solver.solve(timeTable, timeConstraints, precedenceConstraints).getOutputList();

        return new Planning(new HashSet<>(resolvedOutput));
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

        TimeTable timeTable = new TimeTable(dateList, roomList, new ArrayList<>(planning.getSlots()));

        return solver.verify(timeTable, timeConstraints, precedenceConstraints);
    }
}
