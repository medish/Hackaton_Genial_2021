package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.models.User;
import server.services.RegisterService;

@RestController
@RequestMapping(ControllerRoutes.REGISTER)
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping()
    public int registerNewUser(@RequestBody User user) {
        return this.service.insert(user);
    }

}
