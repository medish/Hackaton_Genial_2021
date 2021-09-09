package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.PrecedenceConstraint;
import server.repositories.PrecedenceConstraintRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PrecedenceConstraintService {
    private final PrecedenceConstraintRepository constraintRepository;

    @Autowired
    public PrecedenceConstraintService(PrecedenceConstraintRepository constraintRepository){
        this.constraintRepository = constraintRepository;
    }

    /**
     * Get all precedence constraints
     * @return List of precedence constraints {@link PrecedenceConstraint}
     */
    public List<PrecedenceConstraint> getAll(){
        return constraintRepository.findAll();
    }

    /**
     * Get precedence constraint by ID
     * @param id Precedence constraint ID.
     * @return {@link PrecedenceConstraint}
     */
    public Optional<PrecedenceConstraint> getById(String id){
        return constraintRepository.findById(id);
    }

    /**
     * Insert a precedence constraint record
     * @param constraint {@link PrecedenceConstraint}
     */
    public void insert(PrecedenceConstraint constraint){
        constraintRepository.saveAndFlush(constraint);
    }

    /**
     * Insert multiple precedence constraints records.
     * @param constraints {@link PrecedenceConstraint}
     */
    public void insert(List<PrecedenceConstraint> constraints){
        constraintRepository.saveAllAndFlush(constraints);
    }

    /**
     * Delete a precedence constraint record
     * @param id Precedence constraint ID
     */
    public void delete(String id){
        constraintRepository.deleteById(id);
    }

    /**
     * Delete multiple precedence constraints records
     * @param ids List of ids.
     */
    public void delete(List<String> ids){
        constraintRepository.deleteAllById(ids);
    }

    /**
     * Update a precedence constraint record.
     * @param constraint {@link PrecedenceConstraint}
     * @return The new record of {@link PrecedenceConstraint}
     */
    public PrecedenceConstraint update(PrecedenceConstraint constraint){
        return constraintRepository.saveAndFlush(constraint);
    }




}
