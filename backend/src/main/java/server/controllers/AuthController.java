package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.models.User;
import server.services.UserService;

@RestController
@RequestMapping(ControllerRoutes.SIGNIN)
public class AuthController {
    @Autowired
    UserService userService;

    @GetMapping()
    public User checkAuth(@RequestParam("email") String email, @RequestParam("password") String password) {
        return this.userService.getByEmailAndPassword(email, password);
    }
}
