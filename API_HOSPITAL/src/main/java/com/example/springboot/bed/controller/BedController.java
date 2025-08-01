package com.example.springboot.bed.controller;


import com.example.springboot.bed.DTO.BedDTO;
import com.example.springboot.bed.model.BedModel;
import com.example.springboot.bed.service.BedService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BedController {
    @Autowired private BedService bedService;

    @PostMapping("/beds")
    public ResponseEntity<BedModel> saveBed(@RequestBody @Valid BedDTO bedDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.bedService.save(bedDTO));
    }

    @GetMapping("/beds")
    public ResponseEntity<List<BedModel>> getAllBeds() {
        return ResponseEntity.status(HttpStatus.OK).body(this.bedService.listAll());
    }

    @GetMapping("/bedsSpecialty/{cdSpecialty}")
    public ResponseEntity<List<BedModel>> getBySpecialty(@PathVariable(value="cdSpecialty") Integer cdSpecialty) {
        return ResponseEntity.status(HttpStatus.OK).body(this.bedService.findBySpecialty(cdSpecialty));
    }

    @GetMapping("/beds/{cdBed}")
    public ResponseEntity<Object> getOneBed(@PathVariable(value="cdBed") Long cdBed) {
        return ResponseEntity.status(HttpStatus.OK).body(this.bedService.findById(cdBed));
    }

    @GetMapping("/bedsHistory/{cdBed}")
    public ResponseEntity<Object> getBedHistory(@PathVariable(value="cdBed") Long cdBed) {
        return ResponseEntity.status(HttpStatus.OK).body(this.bedService.findHospitalizationLogByBed(cdBed));
    }

    @PutMapping("/beds/{cdBed}")
    public ResponseEntity<Object> updateBed(@PathVariable(value="cdBed") long cdBed,
                                               @RequestBody @Valid BedDTO bedDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.bedService.update(cdBed, bedDTO));
    }

    @PutMapping("/finishCleaning/{cdBed}")
    public ResponseEntity<Object> finishCleaning(@PathVariable(value="cdBed") Long cdBed) {
        return ResponseEntity.status(HttpStatus.OK).body(this.bedService.finishCleaning(cdBed));
    }

    @DeleteMapping("/beds/{cdBed}")
    public ResponseEntity<Object> deleteBed(@PathVariable(value="cdBed") long cdBed) {
        return ResponseEntity.status(HttpStatus.OK).body(this.bedService.delete(this.bedService.findById(cdBed)));
    }
}
