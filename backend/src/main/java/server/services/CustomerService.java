package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Customer;
import server.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService{
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    /**
     * Gets all customers.
     * @return List of customers {@link Customer}
     */
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    /**
     * Get customer by ID
     * @param id Customer's ID
     * @return {@link Customer}
     */
    public Optional<Customer> getById(String id){
        return customerRepository.findById(id);
    }

    /**
     * Insert a customer record
     * @param customer {@link Customer}
     */
    public void insert(Customer customer){
        customerRepository.saveAndFlush(customer);
    }

    /**
     * Insert multiple customer records
     * @param customers {@link Customer}
     */
    public void insert(List<Customer> customers){
        customerRepository.saveAllAndFlush(customers);
    }

    /**
     * Delete a customer record
     * @param id Customer's ID
     */
    public void delete(String id){
        customerRepository.deleteById(id);
    }

    /**
     * Delete multiple customer records.
     * @param ids List of IDs.
     */
    public void delete(List<String> ids){
        customerRepository.deleteAllById(ids);
    }

    /**
     * Update a customer record
     * @param customer {@link Customer}
     * @return The new record of {@link Customer}
     */
    public Customer update(Customer customer){
        return customerRepository.saveAndFlush(customer);
    }


}
