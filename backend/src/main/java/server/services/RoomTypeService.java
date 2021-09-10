package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.models.RoomType;
import server.repositories.RoomTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeService{
    @Autowired
    private  RoomTypeRepository repository;


    /**
     * Gets all roomTypes.
     * @return List of roomTypes {@link RoomType}
     */
    public List<RoomType> getAll(){
        return repository.findAll();
    }

    /**
     * Get roomType by ID
     * @param id RoomType's ID
     * @return {@link RoomType}
     */
    public Optional<RoomType> getById(Integer id){
        return repository.findById(id);
    }

    /**
     * Insert a roomType record
     * @param roomType {@link RoomType}
     */
    public void insert(RoomType roomType){
        repository.saveAndFlush(roomType);
    }

    /**
     * Insert multiple roomType records
     * @param roomTypes {@link RoomType}
     */
    public void insert(List<RoomType> roomTypes){
        repository.saveAllAndFlush(roomTypes);
    }

    /**
     * Delete a roomType record
     * @param id RoomType's ID
     */
    public void delete(Integer id){
        repository.deleteById(id);
    }

    /**
     * Delete multiple roomType records.
     * @param ids List of IDs.
     */
    public void delete(List<Integer> ids){
        repository.deleteAllById(ids);
    }

    /**
     * Update a roomType record
     * @param roomType {@link RoomType}
     * @return The new record of {@link RoomType}
     */
    public RoomType update(RoomType roomType){
        return repository.saveAndFlush(roomType);
    }


}
