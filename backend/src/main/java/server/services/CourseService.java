package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Course;
import server.repositories.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    /**
     * Gets all courses.
     * @return List of courses {@link Course}
     */
    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    /**
     * Get course by ID
     * @param id Course's ID
     * @return {@link Course}
     */
    public Optional<Course> getById(Integer id){
        return courseRepository.findById(id);
    }

    /**
     * Insert a course record
     * @param course {@link Course}
     */
    public void insert(Course course){
        courseRepository.saveAndFlush(course);
    }

    /**
     * Insert multiple course records
     * @param courses {@link Course}
     */
    public void insert(List<Course> courses){
        courseRepository.saveAllAndFlush(courses);
    }

    /**
     * Delete a course record
     * @param id Course's ID
     */
    public void delete(Integer id){
        courseRepository.deleteById(id);
    }

    /**
     * Delete multiple course records.
     * @param ids List of IDs.
     */
    public void delete(List<Integer> ids){
        courseRepository.deleteAllById(ids);
    }

    /**
     * Update a course record
     * @param course {@link Course}
     * @return The new record of {@link Course}
     */
    public Course update(Course course){
        return courseRepository.saveAndFlush(course);
    }

}
