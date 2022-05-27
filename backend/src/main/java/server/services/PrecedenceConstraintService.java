package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.PrecedenceConstraint;
import server.models.User;
import server.models.UserRole;
import server.repositories.PrecedenceConstraintRepository;
import server.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PrecedenceConstraintService {
    @Autowired
    private PrecedenceConstraintRepository repository;

    @Autowired
    private UserRepository userRepository;
    
    /**
     * Get all precedence constraints
     * @return List of precedence constraints {@link PrecedenceConstraint}
     */
    public List<PrecedenceConstraint> getAll(){
        return repository.findAll();
    }

    /**
     * Get precedence constraint by ID
     * @param id Precedence constraint ID.
     * @return {@link PrecedenceConstraint}
     */
    public Optional<PrecedenceConstraint> getById(int id){
        return repository.findById(id);
    }

    /**
     * Insert a precedence constraint record
     * @param constraint {@link PrecedenceConstraint}
     */
    public void insert(PrecedenceConstraint constraint){
        repository.saveAndFlush(constraint);
    }

    /**
     * Insert multiple precedence constraints records.
     * @param constraints {@link PrecedenceConstraint}
     */
    public void insert(List<PrecedenceConstraint> constraints){
        repository.saveAllAndFlush(constraints);
    }

    /**
     * Delete a precedence constraint record
     * @param id Precedence constraint ID
     */
    public void delete(int id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple precedence constraints records
     * @param ids List of ids.
     */
    public void delete(List<Integer> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a precedence constraint record.
     * @param constraint {@link PrecedenceConstraint}
     * @return The new record of {@link PrecedenceConstraint}
     */
    public PrecedenceConstraint update(PrecedenceConstraint constraint){
        return repository.saveAndFlush(constraint);
    }

    public List<PrecedenceConstraint> getPrecedenceConstraints(int user_id) {
        User user = this.userRepository.findById(user_id).get();

        if (user.getRole().equals(UserRole.ADMIN))
            return this.repository.findAllPrecedenceConstraintsFor();
        return this.repository.findPrecedenceConstraintFor(user);
    }
}
