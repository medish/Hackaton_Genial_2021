/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.acme.schooltimetabling.rest;

import org.acme.schooltimetabling.domain.Degree;
import org.acme.schooltimetabling.domain.Department;
import org.acme.schooltimetabling.domain.Room;
import org.acme.schooltimetabling.domain.RoomType;
import org.acme.schooltimetabling.persistence.DegreeRepository;
import org.acme.schooltimetabling.persistence.DepartmentRepository;
import org.acme.schooltimetabling.persistence.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @RequestMapping("/rooms")
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

   @PostMapping("/room")
    public void addRoom(@RequestBody Map<String, Object> parameters) {

       System.out.println("parameters : " + parameters);

        String roomName = (String) parameters.get("roomName");
        int capacity = Integer.parseInt((String) parameters.get("capacity"));
        Object roomType = parameters.get("roomType");
        long departmentId = Long.parseLong((String) parameters.get("departmentId"));

       System.out.println(roomName);
       System.out.println(capacity);
       System.out.println(roomType);
       System.out.println(departmentId);

       Optional<Department> department = departmentRepository.findById(departmentId);
       System.out.println(department);

       Room room = new Room(roomName, RoomType.SIMPLE, capacity, department.get());
       System.out.println(room);
       roomRepository.save(room);
    }
}
