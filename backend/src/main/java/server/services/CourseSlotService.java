package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.CourseSlot;
import server.repositories.CourseSlotRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseSlotService {
    @Autowired
    private CourseSlotRepository repository;

    /**
     * Gets all CourseSlots.
     * @return List of CourseSlots {@link CourseSlot}
     */
    public List<CourseSlot> getAll(){
        return repository.findAll();
    }

    /**
     * Get CourseSlot by ID
     * @param id CourseSlot's ID
     * @return {@link CourseSlot}
     */
    public Optional<CourseSlot> getById(Integer id){
        return repository.findById(id);
    }

    /**
     * Insert a CourseSlot record
     * @param CourseSlot {@link CourseSlot}
     */
    public void insert(CourseSlot CourseSlot){
        repository.saveAndFlush(CourseSlot);
    }

    /**
     * Insert multiple CourseSlot records
     * @param CourseSlots {@link CourseSlot}
     */
    public void insert(List<CourseSlot> CourseSlots){
        repository.saveAllAndFlush(CourseSlots);
    }

    /**
     * Delete a CourseSlot record
     * @param id CourseSlot's ID
     */
    public void delete(Integer id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple CourseSlot records.
     * @param ids List of IDs.
     */
    public void delete(List<Integer> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a CourseSlot record
     * @param CourseSlot {@link CourseSlot}
     * @return The new record of {@link CourseSlot}
     */
    public CourseSlot update(CourseSlot CourseSlot){
        return repository.saveAndFlush(CourseSlot);
    }

}
