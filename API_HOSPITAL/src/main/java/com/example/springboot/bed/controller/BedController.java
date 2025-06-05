package com.example.springboot.bed.controller;


import com.example.springboot.bed.DTO.BedDTO;
import com.example.springboot.bed.model.BedModel;
import com.example.springboot.bed.service.BedService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BedController {
    @Autowired
    private BedService bedService;

    @PostMapping("/beds")
    public ResponseEntity<BedModel> saveBed(@RequestBody @Valid BedDTO bedDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bedService.save(bedDTO));
    }

    @GetMapping("/beds")
    public ResponseEntity<List<BedModel>> getAllBeds() {
        return ResponseEntity.status(HttpStatus.OK).body(bedService.listAll());
    }

    @GetMapping("/bedsSpecialty/{cdSpecialty}")
    public ResponseEntity<List<BedModel>> getBySpecialty(@PathVariable(value="cdSpecialty") Integer cdSpecialty) {
        return ResponseEntity.status(HttpStatus.OK).body(bedService.findBySpecialty(cdSpecialty));
    }

    @GetMapping("/beds/{cdBed}")
    public ResponseEntity<Object> getOneBed(@PathVariable(value="cdBed") Long cdBed) {
        BedModel bed = bedService.findById(cdBed);
        return ResponseEntity.status(HttpStatus.OK).body(bed);
    }

    @PutMapping("/beds/{cdBed}")
    public ResponseEntity<Object> updateBed(@PathVariable(value="cdBed") long cdBed,
                                               @RequestBody @Valid BedDTO bedDTO) {
        BedModel bed = bedService.findById(cdBed);
        BeanUtils.copyProperties(bedDTO, bed);
        return ResponseEntity.status(HttpStatus.OK).body(bedService.update(bed));
    }

    @DeleteMapping("/beds/{cdBed}")
    public ResponseEntity<Object> deleteBed(@PathVariable(value="cdBed") long cdBed) {
        BedModel bed = bedService.findById(cdBed);
        bedService.delete(bed);
        return ResponseEntity.status(HttpStatus.OK).body("bed deletado com sucesso");
    }
}
