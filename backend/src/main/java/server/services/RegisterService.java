package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Customer;

import server.repositories.CustomerRepository;

@Service
public class RegisterService {


    @Autowired
    private CustomerRepository repository;

    /**
     * Insert a user record
     * @param user {@link Customer}
     */
    public String insert(Customer user){
        Customer newUser = repository.saveAndFlush(user);
        return newUser.getId();
    }
}
