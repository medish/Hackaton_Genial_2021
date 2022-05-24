package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.models.Customer;
import server.services.RegisterService;


@RestController
@RequestMapping(value = ControllerRoutes.REGISTER)
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping(value = "/register")
    public String registerNewUser(@RequestBody Customer user) {
        return this.service.insert(user);
    }

}
