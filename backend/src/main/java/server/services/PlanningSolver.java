package server.services;

import java.util.List;

import org.optaplanner.core.api.score.ScoreExplanation;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.SolverTimeTable;
import core.optaplaner.SolverOptaplaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.output.TimeTable;
import core.planning.DateList;
import server.models.Date;
import server.models.Output;
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
    private LessonService lessonService;
    @Autowired
    private TimeConstraintService timeConstraintService;
    @Autowired
    private PrecedenceConstraintService precedenceConstraintService;

    private static final List<Date> dateList = DateList.build();
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
        List<Output> outputList = TimeTable.buildListOutput(lessonService.getAll());
        // Rooms
        List<Room> roomList = roomService.getAll();

        TimeTable timeTable = new TimeTable(dateList, roomList, outputList);

        List<Output> resolvedOutput = solver.solve(timeTable, timeConstraints, precedenceConstraints).getOutputList();

        return new Planning("0", resolvedOutput);
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

        TimeTable timeTable = new TimeTable(dateList, roomList, planning.getOutputs());

        return solver.verify(timeTable, timeConstraints, precedenceConstraints);
    }
}
