package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import server.models.User;
import server.models.UserRole;
import server.services.RegisterService;

@RestController
@RequestMapping(ControllerRoutes.REGISTER)
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping()
    public int registerNewUser(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName,
            @RequestParam("email") String email, @RequestParam("password") String password) {
        User user = new User(lastName, firstName, email, password, UserRole.GUEST);
        return this.service.insert(user);
    }
}
