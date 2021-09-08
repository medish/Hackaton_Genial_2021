package server.controllers;

import Services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import server.Model.Professor;

import java.util.List;

public class ProfessorController {
    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }
    @GetMapping("/professors")
    public List<Professor> getProfessors(){
    return professorService.getProfessors();
    }
}
