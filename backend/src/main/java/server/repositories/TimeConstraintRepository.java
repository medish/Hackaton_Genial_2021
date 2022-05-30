package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.models.TimeConstraint;
import server.models.User;

import java.util.List;

@Repository
public interface TimeConstraintRepository extends JpaRepository<TimeConstraint, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM time_constraint")
    public List<TimeConstraint> findAllTimeConstraintsFor();

    @Query(nativeQuery = true, value = "SELECT * FROM  time_constraint where creator_id=:user_id")
    public List<TimeConstraint> findTimeConstraintFor(int user_id);
}
