package server.services;

import core.SolverTimeTable;
import core.optaplaner.SolverOptaplaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.output.TimeTable;
import core.planning.DateList;
import org.optaplanner.core.api.score.ScoreExplanation;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import server.models.*;
import server.services.LessonService;
import server.services.PlanningService;
import server.services.RoomService;
import server.services.TimeConstraintService;

import java.util.List;

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
     * @return {@link Planning}
     */
    public Planning solve() {
        // Constraints Time + precedence
        // Lessons
        List<Output> outputList = TimeTable.buildListOutput(lessonService.getAll());
        // Rooms
        List<Room> roomList = roomService.getAll();

        TimeTable timeTable = new TimeTable(dateList, roomList, outputList);

        // TODO add constraints as argument to solve.
        List<Output> resolvedOutput = solver.solve(timeTable).getOutputList();

        return new Planning("0", resolvedOutput);
    }

    /**
     * Checks a planning for a given output list.
     * @param outputList Output list {@link Output}
     * @return {@link ScoreExplanation}
     */
    public ScoreExplanation<TimeTableOptaPlaner, HardSoftScore> checkPlanning(List<Output> outputList){
        // Rooms
        List<Room> roomList = roomService.getAll();

        TimeTable timeTable = new TimeTable(dateList, roomList, outputList);

        // TODO add constraints as argument to verify.
        return solver.verify(timeTable);
    }
}
