package server.controllers;

import core.SolverTimeTable;
import core.optaplaner.SolverOptaplaner;
import core.optaplaner.domain.TimeTableOptaPlaner;
import core.planning.DateList;
import core.output.TimeTable;
import org.optaplanner.core.api.score.ScoreExplanation;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.models.*;
import server.services.LessonService;
import server.services.PlanningService;
import server.services.PlanningSolver;
import server.services.RoomService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ControllerRoutes.PLANNING)
public class PlanningController {
    @Autowired
    private PlanningService service;

    @Autowired
    private PlanningSolver planningSolver;

    @GetMapping()
    public List<Planning> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Planning> getById(String id) {
        return service.getById(id);
    }

    @PostMapping()
    public void insertPlanning(@RequestBody Planning planning) {
        service.insert(planning);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/auto")
    public Planning generatePlanning() {
        return planningSolver.solve();
    }

    @PostMapping("/verify")
    public ScoreExplanation<TimeTableOptaPlaner, HardSoftScore> verifyConstraints(@RequestBody List<Output> outputs) {

        return planningSolver.checkPlanning(outputs);
    }
}
