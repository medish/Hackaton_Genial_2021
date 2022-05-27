package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.models.PrecedenceConstraint;
import server.models.TimeConstraint;
import server.models.User;
import server.services.TimeConstraintService;

import java.util.List;

@RestController
@RequestMapping(ControllerRoutes.CONSTRAINTS_TIME)
public class TimeConstraintController {
    @Autowired
    private  TimeConstraintService service;

    @GetMapping()
    public List<TimeConstraint> getTimeConstraints(@RequestParam("auth") int user_id) {
        return service.getTimeConstraints(user_id);
    }

    @RequestMapping(method = {RequestMethod.DELETE})
    @ResponseBody
    public void deleteTimeConstraint(@RequestParam("id") int id) {
        this.service.delete(id);
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
