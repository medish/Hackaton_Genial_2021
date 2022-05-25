package server.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Planning;
import server.repositories.PlanningRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlanningService {
    @Autowired
    private PlanningRepository repository;

    /**
     * Get all plannings
     * @return List of plannings {@link Planning}
     */
    public List<Planning> getAll(){
        return repository.findAll();
    }

    /**
     * Get planning by ID
     * @param id Planning ID.
     * @return {@link Planning}
     */
    public Optional<Planning> getById(int id){
        return repository.findById(id);
    }

    /**
     * Insert a planning record
     * @param planning {@link Planning}
     */
    public void insert(Planning planning){
        repository.saveAndFlush(planning);
    }

    /**
     * Insert multiple plannings records.
     * @param plannings {@link Planning}
     */
    public void insert(List<Planning> plannings){
        repository.saveAllAndFlush(plannings);
    }

    /**
     * Delete a planning record
     * @param id Planning ID
     */
    public void delete(int id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple plannings records
     * @param ids List of ids.
     */
    public void delete(List<Integer> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a planning record.
     * @param planning {@link Planning}
     * @return The new record of {@link Planning}
     */
    public Planning update(Planning planning){
        return repository.saveAndFlush(planning);
    }
}
