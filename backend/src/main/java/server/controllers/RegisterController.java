package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.models.Customer;
import server.services.RegisterService;


@RestController
@RequestMapping(ControllerRoutes.REGISTER)
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping()
    public String registerNewUser(@RequestBody Customer user) {
        user.setId(user.getFirstName().charAt(0) + user.getName());
        return this.service.insert(user);
    }

}
