package com.example.springboot.hospital.controller;

import com.example.springboot.hospital.DTO.HospitalDTO;
import com.example.springboot.hospital.service.HospitalService;
import com.example.springboot.hospital.model.HospitalModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HospitalController {
    @Autowired private HospitalService hospitalService;

    //Salva um Hospital
    @PostMapping("/hospitals")
    public ResponseEntity<HospitalModel> saveHospital(@RequestBody @Valid HospitalDTO hospitalDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.hospitalService.save(hospitalDTO));
    }

    //Lista todos os hospitais
    @GetMapping("/hospitals")
    public ResponseEntity<List<HospitalModel>> getAllHospitals() {
        return ResponseEntity.status(HttpStatus.OK).body(this.hospitalService.listAll());
    }

    //Busca um hospital pelo id
    @GetMapping("/hospitals/{cdHospital}")
    public ResponseEntity<Object> getOneHospital(@PathVariable(value="cdHospital") Long cdHospital) {
        return ResponseEntity.status(HttpStatus.OK).body(this.hospitalService.findById(cdHospital));
    }

    //Atualiza o Hospital
    @PutMapping("/hospitals/{cdHospital}")
    public ResponseEntity<HospitalModel> updateHospital(@PathVariable(value="cdHospital") long cdHospital,
            @RequestBody @Valid HospitalDTO hospitalDTO) {
        return ResponseEntity.ok(this.hospitalService.update(cdHospital, hospitalDTO));
    }

    //Deleta o Hospital
    @DeleteMapping("/hospitals/{cdHospital}")
    public ResponseEntity<Object> deleteHospital(@PathVariable(value="cdHospital") long cdHospital) {
        return this.hospitalService.delete(cdHospital);
    }
}
