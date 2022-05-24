package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import server.models.Customer;
import server.models.Professor;
import server.repositories.CustomerRepository;
import server.repositories.ProfessorRepository;

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
