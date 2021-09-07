package Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.Reporitories.RoomRepository;

import java.util.List;
@Service
public class RoomService<T>{
    @Autowired
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
/**Returns all rooms in a List format**/
    public List<T> getRooms(){
        return roomRepository.findAll();
    }
}
