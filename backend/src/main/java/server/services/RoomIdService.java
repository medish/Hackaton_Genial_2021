package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.RoomId;
import server.repositories.RoomIdRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomIdService{
    private final RoomIdRepository repository;

    @Autowired
    public RoomIdService(RoomIdRepository repository) {
        this.repository = repository;
    }


    /**
     * Gets all roomIds.
     * @return List of roomIds {@link RoomId}
     */
    public List<RoomId> getAll(){
        return repository.findAll();
    }

    /**
     * Get roomId by ID
     * @param id RoomId's ID
     * @return {@link RoomId}
     */
    public Optional<RoomId> getById(String id){
        return repository.findById(id);
    }

    /**
     * Insert a roomId record
     * @param roomId {@link RoomId}
     */
    public void insert(RoomId roomId){
        repository.saveAndFlush(roomId);
    }

    /**
     * Insert multiple roomId records
     * @param roomIds {@link RoomId}
     */
    public void insert(List<RoomId> roomIds){
        repository.saveAllAndFlush(roomIds);
    }

    /**
     * Delete a roomId record
     * @param id RoomId's ID
     */
    public void delete(String id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple roomId records.
     * @param ids List of IDs.
     */
    public void delete(List<String> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a roomId record
     * @param roomId {@link RoomId}
     * @return The new record of {@link RoomId}
     */
    public RoomId update(RoomId roomId){
        return repository.saveAndFlush(roomId);
    }


}
