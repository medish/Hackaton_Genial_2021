package server.Reporitories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository<T> extends JpaRepository<T,Long> {
}
