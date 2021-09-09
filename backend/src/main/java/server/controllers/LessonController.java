package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.models.Lesson;
import server.services.LessonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ControllerRoutes.LESSON)
public class LessonController {
    @Autowired
    private LessonService service;

    @GetMapping()
    public List<Lesson> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Lesson> getById(@PathVariable String id){
        return service.getById(id);
    }

}
