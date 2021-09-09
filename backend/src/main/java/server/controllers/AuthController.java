package server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerRoutes.SIGNIN)
public class AuthController {

    @GetMapping()
    public boolean checkAuth(@RequestParam("username")String username, @RequestParam("password") String password){
        return true;
    }
}
