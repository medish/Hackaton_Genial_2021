package core.optaplaner;

import java.time.Duration;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;

import core.SolverTimeTable;
import core.optaplaner.domain.LessonOptaPlaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.optaplaner.solver.TimeTableConstraintProvider;
import core.output.TimeTable;

public class SolverOptaplaner implements SolverTimeTable {

	private SolverFactory<TimeTableOptaPlaner> solverFactory;

	public SolverOptaplaner() {
		solverFactory = SolverFactory.create(new SolverConfig().withSolutionClass(TimeTableOptaPlaner.class)
				.withEntityClasses(LessonOptaPlaner.class)
				.withConstraintProviderClass(TimeTableConstraintProvider.class)
				.withTerminationSpentLimit(Duration.ofSeconds(10)));
	}

	@Override
	public TimeTable solve(TimeTable timeTable) {
		TimeTableOptaPlaner timeTableOptaPlaner = TimeTableOptaPlaner.fromInput(timeTable);
		Solver<TimeTableOptaPlaner> solver = solverFactory.buildSolver();
		return solver.solve(timeTableOptaPlaner).toOutput();
	}
}
