package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.models.Department;
import server.services.DepartmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ControllerRoutes.DEPARTMENT)
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @GetMapping()
    public List<Department> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Department> getById(@PathVariable String id){
        return service.getById(id);
    }
}
