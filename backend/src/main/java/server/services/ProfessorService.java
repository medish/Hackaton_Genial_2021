package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Professor;
import server.repositories.ProfessorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService{
    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }


    /**
     * Gets all professors.
     * @return List of professors {@link Professor}
     */
    public List<Professor> getAll(){
        return professorRepository.findAll();
    }

    /**
     * Get professor by ID
     * @param id Professor's ID
     * @return {@link Professor}
     */
    public Optional<Professor> getById(String id){
        return professorRepository.findById(id);
    }

    /**
     * Insert a professor record
     * @param professor {@link Professor}
     */
    public void insert(Professor professor){
        professorRepository.saveAndFlush(professor);
    }

    /**
     * Insert multiple professor records
     * @param professors {@link Professor}
     */
    public void insert(List<Professor> professors){
        professorRepository.saveAllAndFlush(professors);
    }

    /**
     * Delete a professor record
     * @param id Professor's ID
     */
    public void delete(String id){
        professorRepository.deleteById(id);
    }

    /**
     * Delete multiple professor records.
     * @param ids List of IDs.
     */
    public void delete(List<String> ids){
        professorRepository.deleteAllById(ids);
    }

    /**
     * Update a professor record
     * @param professor {@link Professor}
     * @return The new record of {@link Professor}
     */
    public Professor update(Professor professor){
        return professorRepository.saveAndFlush(professor);
    }


}
