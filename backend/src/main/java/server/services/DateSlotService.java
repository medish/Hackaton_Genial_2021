package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.DateSlot;
import server.models.DateSlotId;
import server.repositories.DateSlotRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DateSlotService {
    @Autowired
    private DateSlotRepository repository;

    /**
     * Gets all DateSlots.
     * @return List of DateSlots {@link DateSlot}
     */
    public List<DateSlot> getAll(){
        return repository.findAll();
    }

    /**
     * Get DateSlot by ID
     * @param id DateSlot's ID
     * @return {@link DateSlot}
     */
    public Optional<DateSlot> getById(DateSlotId id){
        return repository.findById(id);
    }

    /**
     * Insert a DateSlot record
     * @param DateSlot {@link DateSlot}
     */
    public void insert(DateSlot DateSlot){
        repository.saveAndFlush(DateSlot);
    }

    /**
     * Insert multiple DateSlot records
     * @param DateSlots {@link DateSlot}
     */
    public void insert(List<DateSlot> DateSlots){
        repository.saveAllAndFlush(DateSlots);
    }

    /**
     * Delete a DateSlot record
     * @param id DateSlot's ID
     */
    public void delete(DateSlotId id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple DateSlot records.
     * @param ids List of IDs.
     */
    public void delete(List<DateSlotId> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a DateSlot record
     * @param DateSlot {@link DateSlot}
     * @return The new record of {@link DateSlot}
     */
    public DateSlot update(DateSlot DateSlot){
        return repository.saveAndFlush(DateSlot);
    }

}
