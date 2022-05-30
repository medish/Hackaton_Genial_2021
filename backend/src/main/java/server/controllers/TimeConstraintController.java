package server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import server.models.TimeConstraint;
import server.services.TimeConstraintService;

@RestController
@RequestMapping(ControllerRoutes.CONSTRAINTS_TIME)
public class TimeConstraintController {

    @Autowired
    private TimeConstraintService service;

    @PostMapping("/all")
    public List<TimeConstraint> insertAll(@RequestBody List<TimeConstraint> constraints) {
        return service.insert(constraints);
    }

    @PostMapping()
    public TimeConstraint insert(@RequestBody TimeConstraint constraint) {
        return service.insert(constraint);
    }

    @PutMapping()
    public TimeConstraint update(@RequestBody TimeConstraint constraint) {
        return service.update(constraint);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTimeConstraint(@RequestParam int id) {
        return this.service.delete(id);
    }

    @DeleteMapping("/delete")
    public boolean deleteAll(@RequestBody List<Integer> ids) {
        return service.delete(ids);
    }

    @GetMapping()
    public List<TimeConstraint> getTimeConstraints(@RequestParam("auth") int user_id) {
        return service.getTimeConstraints(user_id);
    }
}
