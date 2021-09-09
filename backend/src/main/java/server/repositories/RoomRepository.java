package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.Room;
import server.models.RoomId;

@Repository
public interface RoomRepository extends JpaRepository<Room, RoomId> {
}
