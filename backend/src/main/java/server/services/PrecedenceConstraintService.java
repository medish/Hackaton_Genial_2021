package server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.PrecedenceConstraint;
import server.models.User;
import server.models.UserRole;
import server.repositories.PrecedenceConstraintRepository;
import server.repositories.UserRepository;

@Service
public class PrecedenceConstraintService extends AbstractService<PrecedenceConstraint, Integer> {

    @Autowired
    private PrecedenceConstraintRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public JpaRepository<PrecedenceConstraint, Integer> getRepository() {
        return repository;
    }

    public List<PrecedenceConstraint> getPrecedenceConstraints(int user_id) {
        User user = this.userRepository.findById(user_id).get();

        if (user.getRole().equals(UserRole.ADMIN))
            return this.repository.findAllPrecedenceConstraintsFor();
        return this.repository.findPrecedenceConstraintFor(user);
    }
}
