package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.RoomId;

@Repository
public interface RoomIdRepository extends JpaRepository<RoomId, String> {
}
