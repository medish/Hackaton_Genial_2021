package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
