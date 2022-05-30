package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.Course;
import server.repositories.CourseRepository;

@Service
public class CourseService extends AbstractService<Course, Integer> {

    @Autowired
    private CourseRepository repository;

    @Override
    public JpaRepository<Course, Integer> getRepository() {
        return repository;
    }
}
