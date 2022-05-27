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
    public Major insert(@RequestBody Major major) {
        return service.insert(major);
    }

    @PutMapping()
    public Major update(@RequestBody Major major) {
        return service.update(major);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return service.delete(id);
    }
}
