package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.User;
import server.repositories.UserRepository;

@Service
public class UserService extends AbstractService<User, Integer> {

    @Autowired
    private UserRepository repository;

    @Override
    public JpaRepository<User, Integer> getRepository() {
        return repository;
    }

    public User getByEmailAndPassword(String email, String password) {
        return repository.findAll().stream().filter(u -> email.equals(u.getEmail()) && password.equals(u.getPassword()))
                .findFirst().orElse(null);
    }
}
