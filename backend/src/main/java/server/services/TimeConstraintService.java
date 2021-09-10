package server.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.TimeConstraint;
import server.repositories.TimeConstraintRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TimeConstraintService {
    @Autowired
    private TimeConstraintRepository repository;

    /**
     * Get all time constraints
     * @return List of time constraints {@link TimeConstraint}
     */
    public List<TimeConstraint> getAll(){
        return repository.findAll();
    }

    /**
     * Get time constraint by ID
     * @param id Time constraint ID.
     * @return {@link TimeConstraint}
     */
    public Optional<TimeConstraint> getById(String id){
        return repository.findById(id);
    }

    /**
     * Insert a time constraint record
     * @param constraint {@link TimeConstraint}
     */
    public void insert(TimeConstraint constraint){
        repository.saveAndFlush(constraint);
    }

    /**
     * Insert multiple time constraints records.
     * @param constraints {@link TimeConstraint}
     */
    public void insert(List<TimeConstraint> constraints){
        repository.saveAllAndFlush(constraints);
    }

    /**
     * Delete a time constraint record
     * @param id Time constraint ID
     */
    public void delete(String id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple time constraints records
     * @param ids List of ids.
     */
    public void delete(List<String> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a time constraint record.
     * @param constraint {@link TimeConstraint}
     * @return The new record of {@link TimeConstraint}
     */
    public TimeConstraint update(TimeConstraint constraint){
        return repository.saveAndFlush(constraint);
    }




}
