package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.Professor;
import server.repositories.ProfessorRepository;

@Service
public class ProfessorService extends AbstractService<Professor, Integer> {

    @Autowired
    private ProfessorRepository repository;

    @Override
    public JpaRepository<Professor, Integer> getRepository() {
        return repository;
    }
}
