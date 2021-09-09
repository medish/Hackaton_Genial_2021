package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.Degree;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, String> {
}
