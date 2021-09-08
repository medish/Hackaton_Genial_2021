package server.Reporitories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository<T> extends JpaRepository<T,Long> {
}
