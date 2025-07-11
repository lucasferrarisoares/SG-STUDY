package com.example.springboot.room.controller;


import com.example.springboot.room.DTO.RoomDTO;
import com.example.springboot.room.DTO.RoomSpecialtyDTO;
import com.example.springboot.room.DTO.RoomNUDTO;
import com.example.springboot.room.model.RoomModel;
import com.example.springboot.room.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {
    @Autowired private RoomService roomService;

    @PostMapping("/rooms")
    public ResponseEntity<RoomModel> saveRoom(@RequestBody @Valid RoomDTO roomDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.roomService.save(roomDTO));
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomModel>> getAllrooms() {
        return ResponseEntity.status(HttpStatus.OK).body(this.roomService.listAll());
    }

    @GetMapping("/freerooms")
    public ResponseEntity<List<RoomSpecialtyDTO>> getFreeRoom() {
        return ResponseEntity.status(HttpStatus.OK).body(this.roomService.listFreeRoom());
    }

    @GetMapping("/rooms/{cdRoom}")
    public ResponseEntity<Object> getOneroom(@PathVariable(value="cdRoom") long cdRoom) {
        return ResponseEntity.status(HttpStatus.OK).body(this.roomService.findById(cdRoom));
    }

    @GetMapping("/roomsPatient/{cdPatient}")
    public ResponseEntity<Object> getRoomByPatient(@PathVariable(value="cdPatient") long cdPatient) {
        return roomService.findByPatient(cdPatient);
    }

    @GetMapping("/roomSpecialty/{cdSpecialty}")
    public ResponseEntity<RoomNUDTO> getNuRoomBySpecialty(@PathVariable(value="cdSpecialty") Integer cdSpecialty) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.nuRoomBySpecialty(cdSpecialty));
    }

    @PutMapping("/rooms/{cdRoom}")
    public ResponseEntity<Object> updateRoom(@PathVariable(value="cdRoom") long cdRoom, @RequestBody @Valid RoomDTO roomDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.update(cdRoom, roomDTO));
    }

    @DeleteMapping("/rooms/{cdRoom}")
    public ResponseEntity<Object> deleteRoom(@PathVariable(value="cdRoom") long cdRoom) {
        return this.roomService.delete(cdRoom);
    }
}
