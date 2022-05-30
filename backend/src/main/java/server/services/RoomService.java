package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import server.models.Room;
import server.repositories.RoomRepository;

@Service
public class RoomService extends AbstractService<Room, Integer> {

    @Autowired
    private RoomRepository repository;

    @Override
    public JpaRepository<Room, Integer> getRepository() {
        return repository;
    }
}
