package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.models.Output;
import server.models.Planning;
import server.services.PlanningService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ControllerRoutes.PLANNING)
public class PlanningController {
    @Autowired
    private PlanningService service;

    @GetMapping()
    public List<Planning> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Planning> getById(String id){
        return service.getById(id);
    }

    @PostMapping()
    public void insertPlanning(@RequestBody Planning planning){
        service.insert(planning);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }
}
