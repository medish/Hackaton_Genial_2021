package server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.models.Room;
import server.services.RoomService;

@RestController
@RequestMapping(ControllerRoutes.ROOMS)
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

    @PostMapping()
    public boolean insert(@RequestBody Room room) {
        service.insert(room);
        return true;
    }
}
