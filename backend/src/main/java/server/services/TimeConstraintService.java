package server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.TimeConstraint;
import server.models.User;
import server.models.UserRole;
import server.repositories.TimeConstraintRepository;
import server.repositories.UserRepository;

@Service
public class TimeConstraintService extends AbstractService<TimeConstraint, Integer> {

    @Autowired
    private TimeConstraintRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public JpaRepository<TimeConstraint, Integer> getRepository() {
        return repository;
    }

    public List<TimeConstraint> getTimeConstraints(int user_id) {
        User user = this.userRepository.findById(user_id).get();
        if (user.getRole().equals(UserRole.ADMIN))
            return this.repository.findAllTimeConstraintsFor();
        return this.repository.findTimeConstraintFor(user);
    }
}
