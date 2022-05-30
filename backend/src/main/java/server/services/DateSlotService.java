package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.DateSlot;
import server.models.DateSlotId;
import server.repositories.DateSlotRepository;

@Service
public class DateSlotService extends AbstractService<DateSlot, DateSlotId> {

    @Autowired
    private DateSlotRepository repository;

    @Override
    public JpaRepository<DateSlot, DateSlotId> getRepository() {
        return repository;
    }
}
