package server.controllers;

import java.util.List;
import java.util.Optional;

import org.optaplanner.core.api.score.ScoreExplanation;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.optaplaner.domain.TimeTableOptaPlaner;
import server.models.Planning;
import server.services.PlanningService;
import server.services.PlanningSolver;

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
    public ScoreExplanation<TimeTableOptaPlaner, HardSoftScore> verifyConstraints(@RequestBody Planning planning) {
        return planningSolver.checkPlanning(planning);
    }
}
