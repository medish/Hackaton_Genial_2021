package core;

import org.optaplanner.core.api.score.ScoreExplanation;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import core.optaplaner.domain.TimeTableOptaPlaner;
import core.output.TimeTable;

public interface SolverTimeTable {

    public TimeTable solve(TimeTable problem);

    public ScoreExplanation<TimeTableOptaPlaner, HardSoftScore> verify(TimeTable problem);
}
