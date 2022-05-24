package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.models.Course;
import server.services.CourseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ControllerRoutes.COURSES)
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping()
    public List<Course> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Course> getById(@PathVariable Integer id){
        return service.getById(id);
    }
}
