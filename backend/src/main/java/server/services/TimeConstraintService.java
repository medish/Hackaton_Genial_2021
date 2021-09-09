package server.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.TimeConstraint;
import server.repositories.TimeConstraintRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TimeConstraintService {
    private final TimeConstraintRepository constraintRepository;

    @Autowired
    public TimeConstraintService(TimeConstraintRepository constraintRepository){
        this.constraintRepository = constraintRepository;
    }

    /**
     * Get all time constraints
     * @return List of time constraints {@link TimeConstraint}
     */
    public List<TimeConstraint> getAll(){
        return constraintRepository.findAll();
    }

    /**
     * Get time constraint by ID
     * @param id Time constraint ID.
     * @return {@link TimeConstraint}
     */
    public Optional<TimeConstraint> getById(String id){
        return constraintRepository.findById(id);
    }

    /**
     * Insert a time constraint record
     * @param constraint {@link TimeConstraint}
     */
    public void insert(TimeConstraint constraint){
        constraintRepository.saveAndFlush(constraint);
    }

    /**
     * Insert multiple time constraints records.
     * @param constraints {@link TimeConstraint}
     */
    public void insert(List<TimeConstraint> constraints){
        constraintRepository.saveAllAndFlush(constraints);
    }

    /**
     * Delete a time constraint record
     * @param id Time constraint ID
     */
    public void delete(String id){
        constraintRepository.deleteById(id);
    }

    /**
     * Delete multiple time constraints records
     * @param ids List of ids.
     */
    public void delete(List<String> ids){
        constraintRepository.deleteAllById(ids);
    }

    /**
     * Update a time constraint record.
     * @param constraint {@link TimeConstraint}
     * @return The new record of {@link TimeConstraint}
     */
    public TimeConstraint update(TimeConstraint constraint){
        return constraintRepository.saveAndFlush(constraint);
    }




}
