package server.Reporitories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.Model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
