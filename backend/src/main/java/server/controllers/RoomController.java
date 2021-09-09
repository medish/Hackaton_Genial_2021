package server.controllers;

import server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import server.models.Room;

import java.util.List;
import java.util.Optional;

public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @GetMapping("/rooms")
    public List<Room> getRooms(){
        return roomService.getAll();
    }
}
