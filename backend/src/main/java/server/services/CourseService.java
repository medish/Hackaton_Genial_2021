package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Course;
import server.repositories.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repository;
    
    /**
     * Gets all courses.
     * @return List of courses {@link Course}
     */
    public List<Course> getAll(){
        return repository.findAll();
    }

    /**
     * Get course by ID
     * @param id Course's ID
     * @return {@link Course}
     */
    public Optional<Course> getById(Integer id){
        return repository.findById(id);
    }

    /**
     * Insert a course record
     * @param course {@link Course}
     */
    public void insert(Course course){
        repository.saveAndFlush(course);
    }

    /**
     * Insert multiple course records
     * @param courses {@link Course}
     */
    public void insert(List<Course> courses){
        repository.saveAllAndFlush(courses);
    }

    /**
     * Delete a course record
     * @param id Course's ID
     */
    public void delete(Integer id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple course records.
     * @param ids List of IDs.
     */
    public void delete(List<Integer> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a course record
     * @param course {@link Course}
     * @return The new record of {@link Course}
     */
    public Course update(Course course){
        return repository.saveAndFlush(course);
    }

}
