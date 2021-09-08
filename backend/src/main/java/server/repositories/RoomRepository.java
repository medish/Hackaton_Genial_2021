package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.models.Room;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
