package server.Reporitories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.Model.Room;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
