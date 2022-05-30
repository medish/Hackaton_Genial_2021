package server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.models.Course;
import server.services.CourseService;

@RestController
@RequestMapping(ControllerRoutes.COURSES)
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping()
    public List<Course> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Course> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping()
    public Course insert(@RequestBody Course course) {
        return service.insert(course);
    }

    @PutMapping()
    public Course update(@RequestBody Course course) {
        return service.update(course);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return service.delete(id);
    }
}
