package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Department;
import server.repositories.DepartementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartementRepository repository;

    /**
     * Gets all departments.
     *
     * @return List of departments {@link Department}
     */
    public List<Department> getAll() {
        return repository.findAll();
    }

    /**
     * Get department by ID
     *
     * @param id Department's ID
     * @return {@link Department}
     */
    public Optional<Department> getById(String id) {
        return repository.findById(id);
    }

    /**
     * Insert a department record
     *
     * @param department {@link Department}
     */
    public void insert(Department department) {
        repository.saveAndFlush(department);
    }

    /**
     * Insert multiple department records
     *
     * @param departments {@link Department}
     */
    public void insert(List<Department> departments) {
        repository.saveAllAndFlush(departments);
    }

    /**
     * Delete a department record
     *
     * @param id Department's ID
     */
    public void delete(String id) {
        repository.deleteById(id);
    }

    /**
     * Delete multiple department records.
     *
     * @param ids List of IDs.
     */
    public void delete(List<String> ids) {
        repository.deleteAllById(ids);
    }

    /**
     * Update a department record
     *
     * @param department {@link Department}
     * @return The new record of {@link Department}
     */
    public Department update(Department department) {
        return repository.saveAndFlush(department);
    }

}
