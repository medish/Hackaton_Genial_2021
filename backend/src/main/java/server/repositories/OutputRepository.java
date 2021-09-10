package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.models.Output;

public interface OutputRepository extends JpaRepository<Output, String> {
}
