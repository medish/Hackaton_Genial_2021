package server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.models.Professor;
import server.services.ProfessorService;

@RestController
@RequestMapping(value = ControllerRoutes.PROFESSORS)
public class ProfessorController {
    @Autowired
    private ProfessorService service;

    @GetMapping()
    public List<Professor> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Professor> getById(@PathVariable int id) {
        return this.service.getById(id);
    }
}
