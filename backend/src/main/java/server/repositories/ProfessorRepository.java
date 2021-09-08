package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,String> {
}
