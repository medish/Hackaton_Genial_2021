package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.Department;

@Repository
public interface DepartementRepository extends JpaRepository<Department, String> {
}
