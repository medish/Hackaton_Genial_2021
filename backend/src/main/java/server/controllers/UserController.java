package server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import server.models.Professor;
import server.models.User;
import server.models.UserRole;
import server.services.ProfessorService;
import server.services.UserService;

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

        User user = this.userService.getById(id).get();

        user.setEmail(email);
        user.setFirstName(firstname);
        user.setLastName(name);
        user.setRole(UserRole.fromString(role));
        this.userService.update(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable int id) {
        return this.userService.delete(id);
    }


    @PostMapping("/add")
    public User addUser(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName,
            @RequestParam("email") String email, @RequestParam("password") String password,
            @RequestParam("role") String role) {
        UserRole roles = UserRole.fromString(role);
        if (roles == UserRole.PROFESSOR) {
            Professor professor = new Professor(lastName, firstName, email, password);
            return this.professorService.insert(professor);
        }
        User user = new User(lastName, firstName, email, password, roles);
        return this.userService.insert(user);
    }
}
