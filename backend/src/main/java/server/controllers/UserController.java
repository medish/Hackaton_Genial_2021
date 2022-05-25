package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import server.models.User;
import server.services.UserService;

@RestController
@RequestMapping(ControllerRoutes.USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public User updateUser(@RequestParam("name") String name, @RequestParam("firstname") String firstname,
            @RequestParam("email") String email, @RequestParam("id") int id, @RequestParam("isAdmin") boolean isAdmin) {

        User customer = this.userService.getById(id).get();

        customer.setEmail(email);
        customer.setFirstName(firstname);
        customer.setLastName(name);
        this.userService.update(customer);
        return customer;
    }

    @RequestMapping(method = { RequestMethod.DELETE })
    @ResponseBody
    public void deleteUser(@RequestParam("id") int id) {
        this.userService.delete(id);
    }
}
