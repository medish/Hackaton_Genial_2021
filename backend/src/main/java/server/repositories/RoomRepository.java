package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.Department;
import server.models.Room;
import server.models.RoomId;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, RoomId> {
}
