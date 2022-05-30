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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.optaplaner.domain.TimeTableOptaPlaner;
import server.models.Planning;
import server.services.PlanningService;
import server.services.PlanningSolver;

@RestController
@RequestMapping(ControllerRoutes.PLANNINGS)
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
    public Optional<Planning> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping()
    public Planning insert(@RequestBody Planning planning) {
        return service.insert(planning);
    }

    @PutMapping()
        public Planning update(@RequestBody Planning planning) {
        return service.update(planning);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return service.delete(id);
    }

    @GetMapping("/auto/{id}")
    public Planning generatePlanning(@PathVariable int id) {
        return planningSolver.solve(id);
    }

    @PostMapping("/verify/{id}")
    public ScoreExplanation<TimeTableOptaPlaner, HardSoftScore> verifyConstraints(@RequestBody int id) {
        Planning planning = service.getById(id).orElse(null);
        if (planning == null) {
            return null;
        }
        return planningSolver.checkPlanning(planning);
    }
}
