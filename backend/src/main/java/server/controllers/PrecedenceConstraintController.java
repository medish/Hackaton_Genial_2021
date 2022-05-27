package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.models.PrecedenceConstraint;
import server.models.User;
import server.services.PrecedenceConstraintService;

import java.util.List;

@RestController
@RequestMapping(ControllerRoutes.CONSTRAINTS_PRECEDENCE)
public class PrecedenceConstraintController {
    @Autowired
    private PrecedenceConstraintService service;

    @GetMapping()
    public List<PrecedenceConstraint> getPrecedenceConstraints(@RequestParam("auth")int user_id) {
        return service.getPrecedenceConstraints(user_id);
    }

    @RequestMapping(method = {RequestMethod.DELETE})
    @ResponseBody
    public void deleteConstraint(@RequestParam("id") int id) {
        this.service.delete(id);
    }

    @PostMapping()
    public void insertAll(@RequestBody List<PrecedenceConstraint> constraints){
        service.insert(constraints);
    }

    @PostMapping("/delete")
    public void deleteAll(@RequestBody List<Integer> ids){
        service.delete(ids);
    }
}
