package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Day;
import server.repositories.DayRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DayService{
    private final DayRepository repository;

    @Autowired
    public DayService(DayRepository repository) {
        this.repository = repository;
    }


    /**
     * Gets all days.
     * @return List of days {@link Day}
     */
    public List<Day> getAll(){
        return repository.findAll();
    }

    /**
     * Get day by ID
     * @param id Day's ID
     * @return {@link Day}
     */
    public Optional<Day> getById(String id){
        return repository.findById(id);
    }

    /**
     * Insert a day record
     * @param day {@link Day}
     */
    public void insert(Day day){
        repository.saveAndFlush(day);
    }

    /**
     * Insert multiple day records
     * @param days {@link Day}
     */
    public void insert(List<Day> days){
        repository.saveAllAndFlush(days);
    }

    /**
     * Delete a day record
     * @param id Day's ID
     */
    public void delete(String id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple day records.
     * @param ids List of IDs.
     */
    public void delete(List<String> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a day record
     * @param day {@link Day}
     * @return The new record of {@link Day}
     */
    public Day update(Day day){
        return repository.saveAndFlush(day);
    }


}
