package server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.*;
import server.repositories.RoomRepository;
import server.repositories.TimeConstraintRepository;
import server.repositories.UserRepository;

@Service
public class TimeConstraintService extends AbstractService<TimeConstraint, Integer> {

    @Autowired
    private TimeConstraintRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public JpaRepository<TimeConstraint, Integer> getRepository() {
        return repository;
    }

    public List<TimeConstraint> getTimeConstraints(int user_id) {
        User user = this.userRepository.findById(user_id).get();
        if (user.getRole().equals(UserRole.ADMIN))
            return this.repository.findAllTimeConstraintsFor();
        return this.repository.findTimeConstraintFor(user_id);
    }

    @Override
    public List<TimeConstraint> insert(List<TimeConstraint> constraints) {
        List<TimeConstraint> result = new ArrayList<>();
        for(TimeConstraint constraint : constraints) {
            int roomId = this.roomRepository.getRoomId();
            Room room = new Room(roomId);
            constraint.setRoom(room);
            result.add(this.repository.saveAndFlush(constraint));
        }
        return result;
    }
}
