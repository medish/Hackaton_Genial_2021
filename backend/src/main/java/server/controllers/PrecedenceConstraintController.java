package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.models.PrecedenceConstraint;
import server.services.PrecedenceConstraintService;

import java.util.List;

@RestController
@RequestMapping(ControllerRoutes.CONSTRAINTS_PRECEDENCE)
public class PrecedenceConstraintController {
    @Autowired
    private PrecedenceConstraintService service;

    @GetMapping()
    public List<PrecedenceConstraint> getAll(){
        return service.getAll();
    }

    @PostMapping()
    public void insertAll(@RequestBody List<PrecedenceConstraint> constraints){
        service.insert(constraints);
    }


    @PostMapping("/delete")
    public void deleteAll(@RequestBody List<String> ids){
        service.delete(ids);
    }



}
