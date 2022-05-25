package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.models.User;
import server.models.UserRole;
import server.services.UserService;

import java.util.List;

@RestController
@RequestMapping(ControllerRoutes.USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.getAll();
    }

    @PostMapping()
    public User updateUser(@RequestParam("name") String name, @RequestParam("firstname") String firstname,
                           @RequestParam("email") String email, @RequestParam("id") int id, @RequestParam("isAdmin") String role) {

        User customer = this.userService.getById(id).get();

        customer.setEmail(email);
        customer.setFirstName(firstname);
        customer.setLastName(name);
        customer.setRole(UserRole.fromString(role));
        this.userService.update(customer);
        return customer;
    }

    @RequestMapping(method = {RequestMethod.DELETE})
    @ResponseBody
    public void deleteUser(@RequestParam("id") int id) {
        this.userService.delete(id);
    }
}
