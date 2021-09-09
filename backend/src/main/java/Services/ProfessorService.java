package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Professor;
import server.repositories.ProfessorRepository;

import java.util.List;
@Service
public class ProfessorService{
    @Autowired
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> getProfessors(){
        return professorRepository.findAll();
    }
}
