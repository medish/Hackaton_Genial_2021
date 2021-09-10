package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
