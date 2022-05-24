package server.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import server.models.Room;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ControllerRoutes.ROOM)
public class RoomController {
    @Autowired
    private RoomService service;

    @GetMapping()
    public List<Room> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Room> getById(@PathVariable int roomId) {
        return service.getById(roomId);
    }
}
