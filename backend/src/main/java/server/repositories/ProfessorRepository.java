package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
