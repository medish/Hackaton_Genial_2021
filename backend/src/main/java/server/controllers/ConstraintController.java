package server.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.models.Output;

import java.util.List;

@RestController
@RequestMapping(ControllerRoutes.CONSTRAINTS)
public class ConstraintController {

    @PostMapping("/verify")
    public int verifyConstraints(@RequestBody List<Output> outputs){
        return 0;
    }
}
