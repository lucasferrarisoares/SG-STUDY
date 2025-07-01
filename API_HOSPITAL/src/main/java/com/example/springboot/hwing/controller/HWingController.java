package com.example.springboot.hwing.controller;

import com.example.springboot.hwing.DTO.HWingDTO;
import com.example.springboot.hwing.model.HWingModel;
import com.example.springboot.hwing.service.HWingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HWingController {
    @Autowired private HWingService hwingService;

    //Salva uma ala
    @PostMapping("/hwings")
    public ResponseEntity<HWingModel> savehwing(@RequestBody @Valid HWingDTO hwingDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.hwingService.save(hwingDTO));
    }

    //Lista alas
    @GetMapping("/hwings")
    public ResponseEntity<List<HWingModel>> getAllhwings() {
        return ResponseEntity.status(HttpStatus.OK).body(this.hwingService.listAll());
    }

    //Retorna uma ala pelo ID
    @GetMapping("/hwings/{cdHWing}")
    public ResponseEntity<Object> getOnehwing(@PathVariable(value="cdHWing") Long cdHWing) {
        return ResponseEntity.status(HttpStatus.OK).body(this.hwingService.findById(cdHWing));
    }

    //Atualiza uma ala
    @PutMapping("/hwings/{cdHWing}")
    public ResponseEntity<Object> updatehwing(@PathVariable(value="cdHWing") long cdHWing,
                                               @RequestBody @Valid HWingDTO hwingDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(hwingService.update(cdHWing, hwingDTO));
    }

    //Deleta uma Ala
    @DeleteMapping("/hwings/{cdHWing}")
    public ResponseEntity<Object> deletehwing(@PathVariable(value="cdHWing") long cdHWing) {
        return hwingService.delete(cdHWing);
    }
}
