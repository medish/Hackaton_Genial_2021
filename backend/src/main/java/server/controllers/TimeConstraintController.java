package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.models.TimeConstraint;
import server.services.TimeConstraintService;

import java.util.List;

@RestController
@RequestMapping(ControllerRoutes.CONSTRAINTS_TIME)
public class TimeConstraintController {
    @Autowired
    private  TimeConstraintService service;

    @GetMapping()
    public List<TimeConstraint> getAll(){
        return service.getAll();
    }

    @PostMapping()
    public void insertAll(@RequestBody List<TimeConstraint> constraints){
        service.insert(constraints);
    }

    @PostMapping("/delete")
    public void deleteAll(@RequestBody List<Integer> ids){
        service.delete(ids);
    }
}
