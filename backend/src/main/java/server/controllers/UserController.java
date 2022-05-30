package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.models.Professor;
import server.models.User;
import server.models.UserRole;
import server.services.ProfessorService;
import server.services.UserService;

import java.util.List;

@RestController
@RequestMapping(ControllerRoutes.USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfessorService professorService;

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

    @PostMapping(value = "/add")
    public void addUser(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName,
        @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("role") String role) {
        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(UserRole.fromString(role));

        this.userService.insert(user);
    }
}
