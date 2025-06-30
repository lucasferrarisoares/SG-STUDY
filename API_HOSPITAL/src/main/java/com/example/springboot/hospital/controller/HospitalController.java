package com.example.springboot.hospital.controller;


import com.example.springboot.hospital.DTO.HospitalDTO;
import com.example.springboot.hospital.service.HospitalService;
import com.example.springboot.hospital.model.HospitalModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/hospitals")
    public ResponseEntity<HospitalModel> saveHospital(@RequestBody @Valid HospitalDTO hospitalDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.save(hospitalDTO));
    }

    @GetMapping("/hospitals")
    public ResponseEntity<List<HospitalModel>> getAllHospitals() {
        return ResponseEntity.status(HttpStatus.OK).body(hospitalService.listAll());
    }

    @GetMapping("/hospitals/{cdHospital}")
    public ResponseEntity<Object> getOneHospital(@PathVariable(value="cdHospital") Long cdHospital) {
        HospitalModel hospital = hospitalService.findById(cdHospital);
        return ResponseEntity.status(HttpStatus.OK).body(hospital);
    }

    @PutMapping("/hospitals/{cdHospital}")
    public ResponseEntity<HospitalModel> updateHospital(@PathVariable(value="cdHospital") long cdHospital,
            @RequestBody @Valid HospitalDTO hospitalDTO) {
        HospitalModel hospital = hospitalService.findById(cdHospital);
        hospital.setDeHospital(hospitalDTO.deHospital());
        return ResponseEntity.ok(hospitalService.update(hospital));
    }

    @DeleteMapping("/hospitals/{cdHospital}")
    public ResponseEntity<Object> deleteHospital(@PathVariable(value="cdHospital") long cdHospital) {
        hospitalService.delete(hospitalService.findById(cdHospital));
        return ResponseEntity.ok(Collections.singletonMap("mensagem", "hospital deletado com sucesso"));
    }
}
