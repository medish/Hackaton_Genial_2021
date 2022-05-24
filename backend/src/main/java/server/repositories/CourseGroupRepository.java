package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.CourseGroup;

@Repository
public interface CourseGroupRepository extends JpaRepository<CourseGroup, Integer> {
}
