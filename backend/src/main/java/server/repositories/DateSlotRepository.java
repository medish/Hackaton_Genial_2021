package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.models.DateSlot;
import server.models.DateSlotId;

@Repository
public interface DateSlotRepository extends JpaRepository<DateSlot, DateSlotId> {
}
