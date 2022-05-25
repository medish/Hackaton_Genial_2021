package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.PrecedenceConstraint;

@Repository
public interface PrecedenceConstraintRepository extends JpaRepository<PrecedenceConstraint, Integer> {
}
