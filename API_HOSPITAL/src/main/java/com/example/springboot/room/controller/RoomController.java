package com.example.springboot.room.controller;

import com.example.springboot.bed.DTO.BedDTO;
import com.example.springboot.bed.controller.BedController;
import com.example.springboot.hwing.model.HWingModel;
import com.example.springboot.room.DTO.RoomDTO;
import com.example.springboot.room.model.RoomModel;
import com.example.springboot.room.service.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/rooms")
    public ResponseEntity<RoomModel> saveRoom(@RequestBody @Valid RoomDTO roomDTO) {
        RoomModel room = roomService.save(roomDTO);
        generateBed(roomDTO.nuBed(), room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomModel>> getAllrooms() {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.listAll());
    }

    @GetMapping("/rooms/{cdRoom}")
    public ResponseEntity<Object> getOneroom(@PathVariable(value="cdRoom") long cdRoom) {
        RoomModel room = roomService.findById(cdRoom);
        return ResponseEntity.status(HttpStatus.OK).body(room);
    }

    @PutMapping("/rooms/{cdRoom}")
    public ResponseEntity<Object> updateRoom(@PathVariable(value="cdRoom") long cdRoom,
                                               @RequestBody @Valid RoomDTO roomDTO) {
        RoomModel room = roomService.findById(cdRoom);
        BeanUtils.copyProperties(roomDTO, room);
        return ResponseEntity.status(HttpStatus.OK).body(roomService.update(room));
    }

    @DeleteMapping("/rooms/{cdRoom}")
    public ResponseEntity<Object> deleteRoom(@PathVariable(value="cdRoom") long cdRoom) {
        RoomModel room = roomService.findById(cdRoom);
        roomService.delete(room);
        return ResponseEntity.status(HttpStatus.OK).body("Room deletado com sucesso");
    }

    public void generateBed(int nuBed, RoomModel room) {
        BedController bedController = new BedController();
        for (int i = 1; i <= nuBed; i++) {
            BedDTO bed = new BedDTO(0, room.getCdRoom(),room.getDeCodigo()+"-"+i,null);
            bedController.saveBed(bed);
        }
    }
}
