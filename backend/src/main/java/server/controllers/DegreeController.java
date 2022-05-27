package server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.models.Degree;
import server.services.DegreeService;

@RestController
@RequestMapping(ControllerRoutes.DEGREES)
public class DegreeController {

    @Autowired
    private DegreeService service;

    @GetMapping()
    public List<Degree> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Degree> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping()
    public Degree insert(@RequestBody Degree degree) {
        return service.insert(degree);
    }

    @PutMapping()
    public Degree update(@RequestBody Degree degree) {
        return service.update(degree);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return service.delete(id);
    }
}
