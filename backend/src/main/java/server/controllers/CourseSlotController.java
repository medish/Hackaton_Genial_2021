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

import server.models.CourseSlot;
import server.services.CourseSlotService;

@RestController
@RequestMapping(ControllerRoutes.COURSES_SLOTS)
public class CourseSlotController {

    @Autowired
    private CourseSlotService service;

    @GetMapping()
    public List<CourseSlot> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<CourseSlot> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping()
    public CourseSlot insert(@RequestBody CourseSlot courseSlot) {
        return service.insert(courseSlot);
    }

    @PutMapping()
    public CourseSlot update(@RequestBody CourseSlot courseSlot) {
        return service.update(courseSlot);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return service.delete(id);
    }
}
