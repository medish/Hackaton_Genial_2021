package server.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import server.models.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = ControllerRoutes.USERS)
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping()
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<User> getById(@PathVariable int id) {
        return this.service.getById(id);
    }
}
