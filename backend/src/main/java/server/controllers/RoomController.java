package server.controllers;

import Services.ProfessorService;
import Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

public class RoomController<T> {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @GetMapping("/rooms")
    public List<T> getRooms(){
        return roomService.getRooms();
    }

    @GetMapping("/rooms{id}")
    public <T> Optional getRoomsById(Long id){
        return roomService.getRoomsById(id);
    }
}
