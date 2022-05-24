package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.models.CourseSlot;
import server.models.CourseSlotId;
import server.services.CourseSlotService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ControllerRoutes.COURSES_SLOTS)
public class CourseSlotController {

    @Autowired
    private CourseSlotService service;

    @GetMapping()
    public List<CourseSlot> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<CourseSlot> getById(@PathVariable CourseSlotId id){
        return service.getById(id);
    }
}
