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

import server.models.Department;
import server.services.DepartmentService;

@RestController
@RequestMapping(ControllerRoutes.DEPARTMENTS)
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @GetMapping()
    public List<Department> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Department> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping()
    public Department insert(@RequestBody Department department) {
        return service.insert(department);
    }

    @PutMapping()
    public Department update(@RequestBody Department department) {
        return service.update(department);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return service.delete(id);
    }
}
