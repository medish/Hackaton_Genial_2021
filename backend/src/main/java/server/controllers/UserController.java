package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.models.Customer;
import server.services.CustomerService;

@RestController
@RequestMapping(ControllerRoutes.USER)
public class UserController {

    @Autowired
    private CustomerService userService;


    @PostMapping()
    public Customer updateUser(@RequestParam("name") String name, @RequestParam("firstname") String firstname,
                             @RequestParam("email") String email, @RequestParam("id") String id) {

        Customer customer = this.userService.getById(id).get();

        if (customer.getId() != null) {
            customer.setEmail(email);
            customer.setFirstName(firstname);
            customer.setName(name);
            this.userService.update(customer);
        }
        return customer;
    }

    @RequestMapping(method = {RequestMethod.DELETE})
    @ResponseBody
    public void deleteUser(@RequestParam("id") String id) {
        this.userService.delete(id);
    }
}
