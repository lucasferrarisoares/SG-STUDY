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

    @GetMapping("/beds/{cdbed}")
    public ResponseEntity<Object> getOneBed(@PathVariable(value="cdbed") Long cdbed) {
        BedModel bed = bedService.findById(cdbed);
        return ResponseEntity.status(HttpStatus.OK).body(bed);
    }

    @PutMapping("/beds/{cdbed}")
    public ResponseEntity<Object> updateBed(@PathVariable(value="cdbed") long cdbed,
                                               @RequestBody @Valid BedDTO bedDTO) {
        BedModel bed = bedService.findById(cdbed);
        BeanUtils.copyProperties(bedDTO, bed);
        return ResponseEntity.status(HttpStatus.OK).body(bedService.update(bed));
    }

    @DeleteMapping("/beds/{cdbed}")
    public ResponseEntity<Object> deleteBed(@PathVariable(value="cdbed") long cdbed) {
        BedModel bed = bedService.findById(cdbed);
        bedService.delete(bed);
        return ResponseEntity.status(HttpStatus.OK).body("bed deletado com sucesso");
    }
}
