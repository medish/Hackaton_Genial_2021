package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.User;
import server.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService{
    @Autowired
    private  CustomerRepository repository;
    
    /**
     * Gets all customers.
     * @return List of customers {@link User}
     */
    public List<User> getAll(){
        return repository.findAll();
    }

    /**
     * Get customer by ID
     * @param id Customer's ID
     * @return {@link User}
     */
    public Optional<User> getById(String id){
        return repository.findById(id);
    }

    /**
     * Insert a customer record
     * @param customer {@link User}
     */
    public void insert(User customer){
        repository.saveAndFlush(customer);
    }

    /**
     * Insert multiple customer records
     * @param customers {@link User}
     */
    public void insert(List<User> customers){
        repository.saveAllAndFlush(customers);
    }

    /**
     * Delete a customer record
     * @param id Customer's ID
     */
    public void delete(String id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple customer records.
     * @param ids List of IDs.
     */
    public void delete(List<String> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a customer record
     * @param customer {@link User}
     * @return The new record of {@link User}
     */
    public User update(User customer){
        return repository.saveAndFlush(customer);
    }


}
