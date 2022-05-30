package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.CourseSlot;
import server.repositories.CourseSlotRepository;

@Service
public class CourseSlotService extends AbstractService<CourseSlot, Integer> {

    @Autowired
    private CourseSlotRepository repository;

    @Override
    public JpaRepository<CourseSlot, Integer> getRepository() {
        return repository;
    }
}
