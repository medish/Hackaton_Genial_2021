package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.models.CourseGroup;
import server.models.CourseGroupId;
import server.services.CourseGroupService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ControllerRoutes.COURSES_GROUPS)
public class CourseGroupController {

    @Autowired
    private CourseGroupService service;

    @GetMapping()
    public List<CourseGroup> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<CourseGroup> getById(@PathVariable CourseGroupId id){
        return service.getById(id);
    }
}
