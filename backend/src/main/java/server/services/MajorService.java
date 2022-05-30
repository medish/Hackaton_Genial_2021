package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.Major;
import server.repositories.MajorRepository;

@Service
public class MajorService extends AbstractService<Major, Integer> {

    @Autowired
    private MajorRepository repository;

    @Override
    public JpaRepository<Major, Integer> getRepository() {
        return repository;
    }

}
