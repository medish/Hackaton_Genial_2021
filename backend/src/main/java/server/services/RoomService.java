package server.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import server.models.Room;
import server.models.RoomId;
import server.repositories.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Get all room records
     * @return List of rooms {@link Room}
     */
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    /**
     * Get room by {@link RoomId}
     * @param roomId see {@link RoomId}
     * @return Room ID
     */
    public Optional<Room> getById(RoomId roomId) {
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
     * @param roomId {@link RoomId}
     */
    public void delete(RoomId roomId){
        roomRepository.deleteById(roomId);
    }

    /**
     * Delete multiple room records
     * @param roomIds List of rooms ids.
     */
    public void delete(List<RoomId> roomIds){
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
