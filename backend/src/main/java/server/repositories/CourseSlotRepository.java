package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.CourseSlot;

@Repository
public interface CourseSlotRepository extends JpaRepository<CourseSlot, Integer> {
}
