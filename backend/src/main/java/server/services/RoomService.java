package server.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.Room;
import server.repositories.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    /**
     * Get all room records
     * @return List of rooms {@link Room}
     */
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    /**
     * Get room by id
     * @param roomId
     * @return Room ID
     */
    public Optional<Room> getById(int roomId) {
        return roomRepository.findById(roomId);
    }

    /**
     * Insert a room record.
     * @param room {@link Room}
     */
    public void insert(Room room){
        roomRepository.saveAndFlush(room);
    }

    /**
     * Insert multiple room records
     * @param rooms {@link Room}
     */
    public void insert(List<Room> rooms){
        roomRepository.saveAllAndFlush(rooms);
    }

    /**
     * Delete a room record
     * @param roomId
     */
    public void delete(int roomId){
        roomRepository.deleteById(roomId);
    }

    /**
     * Delete multiple room records
     * @param roomIds List of rooms ids.
     */
    public void delete(List<Integer> roomIds){
        roomRepository.deleteAllById(roomIds);
    }

    /**
     * Update a room record.
     * @param room {@link Room}
     * @return The new record of {@link Room}
     */
    public Room update(Room room){
        return roomRepository.saveAndFlush(room);
    }
}
