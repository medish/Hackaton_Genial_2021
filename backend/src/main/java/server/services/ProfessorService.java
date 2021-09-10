package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Professor;
import server.repositories.ProfessorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService{

    @Autowired
    private ProfessorRepository repository;
    
    /**
     * Gets all professors.
     * @return List of professors {@link Professor}
     */
    public List<Professor> getAll(){
        return repository.findAll();
    }

    /**
     * Get professor by ID
     * @param id Professor's ID
     * @return {@link Professor}
     */
    public Optional<Professor> getById(String id){
        return repository.findById(id);
    }

    /**
     * Insert a professor record
     * @param professor {@link Professor}
     */
    public void insert(Professor professor){
        repository.saveAndFlush(professor);
    }

    /**
     * Insert multiple professor records
     * @param professors {@link Professor}
     */
    public void insert(List<Professor> professors){
        repository.saveAllAndFlush(professors);
    }

    /**
     * Delete a professor record
     * @param id Professor's ID
     */
    public void delete(String id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple professor records.
     * @param ids List of IDs.
     */
    public void delete(List<String> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a professor record
     * @param professor {@link Professor}
     * @return The new record of {@link Professor}
     */
    public Professor update(Professor professor){
        return repository.saveAndFlush(professor);
    }


}
