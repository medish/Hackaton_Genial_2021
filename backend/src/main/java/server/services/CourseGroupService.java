package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.CourseGroup;
import server.repositories.CourseGroupRepository;

@Service
public class CourseGroupService extends AbstractService<CourseGroup, Integer> {

    @Autowired
    private CourseGroupRepository repository;

    @Override
    public JpaRepository<CourseGroup, Integer> getRepository() {
        return repository;
    }
}
