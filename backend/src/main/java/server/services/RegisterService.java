package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.models.User;
import server.repositories.UserRepository;

@Service
public class RegisterService {

    @Autowired
    private UserRepository repository;

    /**
     * Insert a user record
     * 
     * @param user {@link Customer}
     */
    public int insert(User user) {
        User newUser = repository.saveAndFlush(user);
        return newUser.getId();
    }
}
