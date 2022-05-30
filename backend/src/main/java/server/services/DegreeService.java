package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.Degree;
import server.repositories.DegreeRepository;

@Service
public class DegreeService extends AbstractService<Degree, Integer> {

    @Autowired
    private DegreeRepository repository;

    @Override
    public JpaRepository<Degree, Integer> getRepository() {
        return repository;
    }
}
