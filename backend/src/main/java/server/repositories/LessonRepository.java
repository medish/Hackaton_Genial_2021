package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, String> {
}
