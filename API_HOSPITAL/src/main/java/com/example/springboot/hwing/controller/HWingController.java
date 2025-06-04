package com.example.springboot.hwing.controller;


import com.example.springboot.bed.DTO.BedDTO;
import com.example.springboot.bed.service.BedService;
import com.example.springboot.enumerated.status.Status;
import com.example.springboot.hwing.DTO.HWingDTO;
import com.example.springboot.hwing.model.HWingModel;
import com.example.springboot.hwing.service.HWingService;
import com.example.springboot.room.DTO.RoomDTO;
import com.example.springboot.room.controller.RoomController;
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
public class HWingController {
    @Autowired
    private HWingService hwingService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BedService bedService;


    @PostMapping("/hwings")
    public ResponseEntity<HWingModel> savehwing(@RequestBody @Valid HWingDTO hwingDTO) {
        HWingModel wing = hwingService.save(hwingDTO);
        generateRoom(hwingDTO.nuRoom(), wing, hwingDTO.nuBed());
        return ResponseEntity.status(HttpStatus.CREATED).body(wing);
    }

    @GetMapping("/hwings")
    public ResponseEntity<List<HWingModel>> getAllhwings() {
        return ResponseEntity.status(HttpStatus.OK).body(hwingService.listAll());
    }

    @GetMapping("/hwings/{cdHWing}")
    public ResponseEntity<Object> getOnehwing(@PathVariable(value="cdHWing") Long cdHWing) {
        HWingModel hwing = hwingService.findById(cdHWing);
        return ResponseEntity.status(HttpStatus.OK).body(hwing);
    }

    @PutMapping("/hwings/{cdHWing}")
    public ResponseEntity<Object> updatehwing(@PathVariable(value="cdHWing") long cdHWing,
                                               @RequestBody @Valid HWingDTO hwingDTO) {
        HWingModel hwing = hwingService.findById(cdHWing);
        BeanUtils.copyProperties(hwingDTO, hwing);
        return ResponseEntity.status(HttpStatus.OK).body(hwingService.update(hwing));
    }

    @DeleteMapping("/hwings/{cdHWing}")
    public ResponseEntity<Object> deletehwing(@PathVariable(value="cdHWing") long cdHWing) {
        HWingModel hwing = hwingService.findById(cdHWing);
        hwingService.delete(hwing);
        return ResponseEntity.status(HttpStatus.OK).body("hwing deletado com sucesso");
    }

    public void generateRoom(int nuRoom, HWingModel wing, int nuBed) {
        for (int i = 1; i <= nuRoom; i++) {
            RoomDTO roomDTO = new RoomDTO(wing.getDeSpecialty().getDeName().substring(0,3) + i,
                    0, wing.getCdHWing(), nuBed);
            RoomModel room = roomService.save(roomDTO);
            generateBed(room, nuBed);
        }
    }

    public void generateBed(RoomModel room, int nuBed) {
        for (int i = 1; i <= nuBed; i++) {
            BedDTO bedDTO = new BedDTO(null, room.getCdRoom(), room.getDeCodigo() + "-"+ i, null);
            bedService.save(bedDTO);
        }
    }
}
