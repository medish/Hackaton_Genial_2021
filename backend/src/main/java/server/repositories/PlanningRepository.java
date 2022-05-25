package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.Planning;

@Repository
public interface PlanningRepository extends JpaRepository<Planning, Integer> {
}
