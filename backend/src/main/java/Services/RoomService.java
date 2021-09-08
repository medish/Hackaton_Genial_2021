package Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.Model.Room;
import server.Reporitories.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService{
    @Autowired
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
/**Returns all rooms in a List format**/
    public List<Room> getRooms(){
        return roomRepository.findAll();
    }

    public  Optional getRoomsById(Long id) {
        return roomRepository.findById(id);
    }
}
