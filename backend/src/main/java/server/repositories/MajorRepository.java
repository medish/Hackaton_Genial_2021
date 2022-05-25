package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.Major;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {
}
