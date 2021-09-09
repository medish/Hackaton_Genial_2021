package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Lesson;
import server.repositories.LessonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService{
    private final LessonRepository repository;

    @Autowired
    public LessonService(LessonRepository repository) {
        this.repository = repository;
    }


    /**
     * Gets all lessons.
     * @return List of lessons {@link Lesson}
     */
    public List<Lesson> getAll(){
        return repository.findAll();
    }

    /**
     * Get lesson by ID
     * @param id Lesson's ID
     * @return {@link Lesson}
     */
    public Optional<Lesson> getById(String id){
        return repository.findById(id);
    }

    /**
     * Insert a lesson record
     * @param lesson {@link Lesson}
     */
    public void insert(Lesson lesson){
        repository.saveAndFlush(lesson);
    }

    /**
     * Insert multiple lesson records
     * @param lessons {@link Lesson}
     */
    public void insert(List<Lesson> lessons){
        repository.saveAllAndFlush(lessons);
    }

    /**
     * Delete a lesson record
     * @param id Lesson's ID
     */
    public void delete(String id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple lesson records.
     * @param ids List of IDs.
     */
    public void delete(List<String> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a lesson record
     * @param lesson {@link Lesson}
     * @return The new record of {@link Lesson}
     */
    public Lesson update(Lesson lesson){
        return repository.saveAndFlush(lesson);
    }


}
