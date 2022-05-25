package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.TimeConstraint;

@Repository
public interface TimeConstraintRepository extends JpaRepository<TimeConstraint, Integer> {
}
