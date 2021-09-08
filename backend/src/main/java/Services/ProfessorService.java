package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.Model.Professor;
import server.Reporitories.ProfessorRepository;

import java.util.List;
@Service
public class ProfessorService{
    @Autowired
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

/**Returns all Professors in a List format **/

    public List<Professor> getProfessors(){
        return professorRepository.findAll();
    }
}
