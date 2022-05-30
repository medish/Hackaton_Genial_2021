package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.Planning;
import server.repositories.PlanningRepository;

@Service
public class PlanningService extends AbstractService<Planning, Integer> {

    @Autowired
    private PlanningRepository repository;

    @Override
    public JpaRepository<Planning, Integer> getRepository() {
        return repository;
    }
}
