package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.Department;
import server.repositories.DepartmentRepository;

@Service
public class DepartmentService extends AbstractService<Department, Integer> {

    @Autowired
    private DepartmentRepository repository;

    @Override
    public JpaRepository<Department, Integer> getRepository() {
        return repository;
    }
}
