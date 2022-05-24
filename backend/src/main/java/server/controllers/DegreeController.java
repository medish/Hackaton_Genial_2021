package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.models.Degree;
import server.services.DegreeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ControllerRoutes.DEGREES)
public class DegreeController {

    @Autowired
    private DegreeService service;

    @GetMapping()
    public List<Degree> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Degree> getById(@PathVariable int id){
        return service.getById(id);
    }
}
