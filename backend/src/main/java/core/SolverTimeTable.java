package core;

import java.util.List;

import org.optaplanner.core.api.score.ScoreExplanation;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import core.optaplaner.domain.TimeTableOptaPlaner;
import core.output.TimeTable;
import server.models.PrecedenceConstraint;
import server.models.TimeConstraint;

public interface SolverTimeTable {

    public TimeTable solve(TimeTable problem, List<TimeConstraint> timeConstraints,
            List<PrecedenceConstraint> precedenceConstraints);

    public ScoreExplanation<TimeTableOptaPlaner, HardSoftScore> verify(TimeTable problem,
            List<TimeConstraint> timeConstraints, List<PrecedenceConstraint> precedenceConstraints);
}
