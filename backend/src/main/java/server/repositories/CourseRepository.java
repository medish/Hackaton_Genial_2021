package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
