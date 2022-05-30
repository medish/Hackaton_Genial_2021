package server.controllers;

import java.util.List;
import java.util.Optional;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.models.CourseGroup;
import server.services.CourseGroupService;

@RestController
@RequestMapping(ControllerRoutes.COURSES_GROUPS)
public class CourseGroupController {

    @Autowired
    private CourseGroupService service;

    @GetMapping()
    public List<CourseGroup> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<CourseGroup> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping()
    public CourseGroup insert(@RequestBody CourseGroup courseGroup) {
        return service.insert(courseGroup);
    }

    @PutMapping()
    public CourseGroup update(@RequestBody CourseGroup courseGroup) {
        return service.update(courseGroup);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return service.delete(id);
    }
}
