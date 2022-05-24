package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.DateSlot;

@Repository
public interface DateSlotRepository extends JpaRepository<DateSlot, Integer> {
}
