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

import server.models.PrecedenceConstraint;
import server.services.PrecedenceConstraintService;

@RestController
@RequestMapping(ControllerRoutes.CONSTRAINTS_PRECEDENCE)
public class PrecedenceConstraintController {

    @Autowired
    private PrecedenceConstraintService service;

    @PostMapping("/all")
    public List<PrecedenceConstraint> insertAll(@RequestBody List<PrecedenceConstraint> constraints) {
        return service.insert(constraints);
    }

    @PostMapping()
    public PrecedenceConstraint insert(@RequestBody PrecedenceConstraint constraint) {
        return service.insert(constraint);
    }

    @PutMapping()
    public PrecedenceConstraint update(@RequestBody PrecedenceConstraint constraint) {
        return service.update(constraint);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@RequestParam int id) {
        return this.service.delete(id);
    }

    @DeleteMapping("/delete")
    public boolean deleteAll(@RequestBody List<Integer> ids) {
        return service.delete(ids);
    }

    @GetMapping()
    public List<PrecedenceConstraint> getPrecedenceConstraints(@RequestParam("auth") int user_id) {
        return service.getPrecedenceConstraints(user_id);
    }
}
