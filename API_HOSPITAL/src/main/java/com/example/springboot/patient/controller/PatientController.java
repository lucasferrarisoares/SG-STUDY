package com.example.springboot.patient.controller;


import com.example.springboot.bed.service.BedService;
import com.example.springboot.hospitalizationslog.service.HospitalizationsLogService;
import com.example.springboot.patient.DTO.PatientDTO;
import com.example.springboot.patient.model.PatientModel;
import com.example.springboot.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private BedService bedService;
    @Autowired
    private HospitalizationsLogService hospitalizationsLogService;

    @PostMapping("/patients")
    public ResponseEntity<PatientModel> savepatient(@RequestBody @Valid PatientDTO patientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.save(patientDTO));
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientModel>> getAllpatients() {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.listAll());
    }

    @GetMapping("/patients/{cdPatient}")
    public ResponseEntity<Object> getOnepatient(@PathVariable(value="cdPatient") Long cdPatient) {
        PatientModel patient = patientService.findById(cdPatient);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

    @GetMapping("/historicHospitalization/{cdPatient}")
    public ResponseEntity<Object> getHistoryHospitalizationInfo(
            @PathVariable("cdPatient") Long cdPatient,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(
                patientService.findHistoryHospitalization(cdPatient, pageable));
    }

    @GetMapping("/patientsHospitalization/{cdPatient}")
    public ResponseEntity<Object> getPatientHospitalizationInfo(@PathVariable(value="cdPatient") Long cdPatient) {
        return ResponseEntity.ok(this.patientService.findPatientHospitalizationInfo(cdPatient));
    }

    @PutMapping("/patients/{cdPatient}")
    public ResponseEntity<Object> updatepatient(@PathVariable(value="cdPatient") long cdPatient,
                                               @RequestBody @Valid PatientDTO patientDTO) {
        PatientModel patient = patientService.findById(cdPatient);
        BeanUtils.copyProperties(patientDTO, patient);
        return ResponseEntity.status(HttpStatus.OK).body(patientService.update(patient));
    }

    @DeleteMapping("/patients/{cdPatient}")
    public ResponseEntity<Object> deletepatient(@PathVariable(value="cdPatient") long cdPatient) {
        patientService.delete(patientService.findById(cdPatient));
        return ResponseEntity.status(HttpStatus.OK).body("patient deletado com sucesso");
    }

    @PostMapping("/hospitalizations/{cdPatient}/{cdSpecialty}")
    public ResponseEntity<Object> hospitalizationPacient(@PathVariable(value="cdPatient") Long cdPatient,
                                                         @PathVariable(value="cdSpecialty") int cdSpecialty) {
        return patientService.hospitalizationPaciente(cdPatient, cdSpecialty);
    }

    @PutMapping("/releasePatient/{cdHospitalization}")
    public ResponseEntity<Object>  releasePatient(@PathVariable(value="cdPatient") Long cdHospitalization) {
        return ResponseEntity.status(HttpStatus.OK).body(this.patientService.releasePatient(cdHospitalization));
    }
}
