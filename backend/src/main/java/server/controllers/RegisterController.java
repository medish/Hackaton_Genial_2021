package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(UserRole.PROFESSOR);

        return this.service.insert(user);
    }

}
