package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,String> {
    @Query(nativeQuery = true, value = "DELETE FROM LESSON_PROFESSOR where professor_id=:profId")
    @Modifying
    void removeLessonProfessorMappingt(@Param("profId")String profId);
}
