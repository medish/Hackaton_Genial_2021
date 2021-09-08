package server.controllers;

import Services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class ProfessorController<T> {
    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }
    @GetMapping("/professors")
    public List<T> getProfessors(){
    return professorService.getProfessors();
    }
}
