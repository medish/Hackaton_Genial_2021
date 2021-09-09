package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Customer;
import server.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService{
    @Autowired
    private  CustomerRepository repository;
    
    /**
     * Gets all customers.
     * @return List of customers {@link Customer}
     */
    public List<Customer> getAll(){
        return repository.findAll();
    }

    /**
     * Get customer by ID
     * @param id Customer's ID
     * @return {@link Customer}
     */
    public Optional<Customer> getById(String id){
        return repository.findById(id);
    }

    /**
     * Insert a customer record
     * @param customer {@link Customer}
     */
    public void insert(Customer customer){
        repository.saveAndFlush(customer);
    }

    /**
     * Insert multiple customer records
     * @param customers {@link Customer}
     */
    public void insert(List<Customer> customers){
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
     * @param customer {@link Customer}
     * @return The new record of {@link Customer}
     */
    public Customer update(Customer customer){
        return repository.saveAndFlush(customer);
    }


}
