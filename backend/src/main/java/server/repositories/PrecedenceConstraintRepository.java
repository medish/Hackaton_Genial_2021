package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.models.PrecedenceConstraint;
import server.models.User;

import java.util.List;

@Repository
public interface PrecedenceConstraintRepository extends JpaRepository<PrecedenceConstraint, Integer> {
    @Query(nativeQuery = true, value = "SELECT * from precedence_constraint")
    public List<PrecedenceConstraint> findAllPrecedenceConstraintsFor();

    @Query(nativeQuery = true, value = "SELECT * FROM precedence_constraint where creator_id=:user_id")
    public List<PrecedenceConstraint> findPrecedenceConstraintFor(int user_id);
}
