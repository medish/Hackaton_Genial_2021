package server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.models.Major;
import server.services.MajorService;

@RestController
@RequestMapping(ControllerRoutes.MAJORS)
public class MajorController {

    @Autowired
    private MajorService service;

    @GetMapping()
    public List<Major> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Major> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping()
    public boolean insert(@RequestBody Major major) {
        service.insert(major);
        return true;
    }
}
