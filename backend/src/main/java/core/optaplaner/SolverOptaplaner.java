package core.optaplaner;

import core.SolverTimeTable;
import core.optaplaner.domain.LessonOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.optaplaner.solver.TimeTableConstraintProvider;
import core.output.TimeTable;
import org.optaplanner.core.api.score.ScoreExplanation;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import server.models.PrecedenceConstraint;
import server.models.TimeConstraint;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SolverOptaplaner implements SolverTimeTable {
    private SolverFactory<TimeTableOptaPlaner> solverFactory;

    private static List<Function<ConstraintFactory, Constraint>> constraints;

    public static List<Constraint> getConstraints(ConstraintFactory constraintFactory) {
        return constraints.stream().map(constraint -> constraint.apply(constraintFactory)).toList();
    }

    public static void setConstraints(List<TimeConstraint> timeConstraints,
            List<PrecedenceConstraint> precedenceConstraints) {
        constraints = new ArrayList<Function<ConstraintFactory, Constraint>>();
        for (TimeConstraint timeConstraint : timeConstraints) {
            constraints.add(
                    constraintFactory -> TimeTableConstraintProvider.TimeConstraint(constraintFactory, timeConstraint));
        }
        for (PrecedenceConstraint precedenceConstraint : precedenceConstraints) {
            constraints.add(constraintFactory -> TimeTableConstraintProvider.PrecedenceConstraint(constraintFactory,
                    precedenceConstraint));
        }
    }

    public SolverOptaplaner() {
        solverFactory = SolverFactory.create(new SolverConfig().withSolutionClass(TimeTableOptaPlaner.class)
                .withEntityClasses(LessonOptaPlaner.class)
                .withConstraintProviderClass(TimeTableConstraintProvider.class)
                .withTerminationSpentLimit(Duration.ofSeconds(10)));
    }

    @Override
    public TimeTable solve(TimeTable timeTable, List<TimeConstraint> timeConstraints,
            List<PrecedenceConstraint> precedenceConstraints) {
        TimeTableOptaPlaner timeTableOptaPlaner = TimeTableOptaPlaner.fromInput(timeTable);
        setConstraints(timeConstraints, precedenceConstraints);
        Solver<TimeTableOptaPlaner> solver = solverFactory.buildSolver();
        TimeTable timeTableResult = solver.solve(timeTableOptaPlaner).toOutput();
        System.out.println(timeTableResult);
        return timeTableResult;
    }

    @Override
    public ScoreExplanation<TimeTableOptaPlaner, HardSoftScore> verify(TimeTable timeTable,
            List<TimeConstraint> timeConstraints, List<PrecedenceConstraint> precedenceConstraints) {
        TimeTableOptaPlaner timeTableOptaPlaner = TimeTableOptaPlaner.fromInput(timeTable);
        setConstraints(timeConstraints, precedenceConstraints);
        ScoreManager<TimeTableOptaPlaner, HardSoftScore> scoreManager = ScoreManager.create(solverFactory);
        ScoreExplanation<TimeTableOptaPlaner, HardSoftScore> scoreExplanation = scoreManager
                .explainScore(timeTableOptaPlaner);
        return scoreExplanation;
    }
}
