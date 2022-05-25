package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Major;
import server.repositories.MajorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MajorService {
    @Autowired
    private MajorRepository repository;

    /**
     * Gets all Majors.
     *
     * @return List of Majors {@link Major}
     */
    public List<Major> getAll() {
        return repository.findAll();
    }

    /**
     * Get Major by ID
     *
     * @param id Major's ID
     * @return {@link Major}
     */
    public Optional<Major> getById(int id) {
        return repository.findById(id);
    }

    /**
     * Insert a Major record
     *
     * @param Major {@link Major}
     */
    public void insert(Major Major) {
        repository.saveAndFlush(Major);
    }

    /**
     * Insert multiple Major records
     *
     * @param Majors {@link Major}
     */
    public void insert(List<Major> Majors) {
        repository.saveAllAndFlush(Majors);
    }

    /**
     * Delete a Major record
     *
     * @param id Major's ID
     */
    public void delete(int id) {
        repository.deleteById(id);
    }

    /**
     * Delete multiple Major records.
     *
     * @param ids List of IDs.
     */
    public void delete(List<Integer> ids) {
        repository.deleteAllById(ids);
    }

    /**
     * Update a Major record
     *
     * @param Major {@link Major}
     * @return The new record of {@link Major}
     */
    public Major update(Major Major) {
        return repository.saveAndFlush(Major);
    }

}
