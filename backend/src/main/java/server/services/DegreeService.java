package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Degree;
import server.repositories.DegreeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DegreeService{
    @Autowired
    private DegreeRepository repository;

    /**
     * Gets all degrees.
     * @return List of degrees {@link Degree}
     */
    public List<Degree> getAll(){
        return repository.findAll();
    }

    /**
     * Get degree by ID
     * @param id Degree's ID
     * @return {@link Degree}
     */
    public Optional<Degree> getById(int id){
        return repository.findById(id);
    }

    /**
     * Insert a degree record
     * @param degree {@link Degree}
     */
    public void insert(Degree degree){
        repository.saveAndFlush(degree);
    }

    /**
     * Insert multiple degree records
     * @param degrees {@link Degree}
     */
    public void insert(List<Degree> degrees){
        repository.saveAllAndFlush(degrees);
    }

    /**
     * Delete a degree record
     * @param id Degree's ID
     */
    public void delete(int id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple degree records.
     * @param ids List of IDs.
     */
    public void delete(List<Integer> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a degree record
     * @param degree {@link Degree}
     * @return The new record of {@link Degree}
     */
    public Degree update(Degree degree){
        return repository.saveAndFlush(degree);
    }


}
