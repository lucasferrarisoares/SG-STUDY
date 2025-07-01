package com.example.springboot.patient.controller;

import com.example.springboot.patient.DTO.PatientDTO;
import com.example.springboot.patient.model.PatientModel;
import com.example.springboot.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
public class PatientController {
    @Autowired private PatientService patientService;

    //Salva um paciente
    @PostMapping("/patients")
    public ResponseEntity<PatientModel> savepatient(@RequestBody @Valid PatientDTO patientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.patientService.save(patientDTO));
    }

    //Lista todos os pacientes
    @GetMapping("/patients")
    public ResponseEntity<List<PatientModel>> getAllpatients() {
        return ResponseEntity.status(HttpStatus.OK).body(this.patientService.listAll());
    }

    //Seleciona uma paciente pelo ID
    @GetMapping("/patients/{cdPatient}")
    public ResponseEntity<Object> getOnepatient(@PathVariable(value="cdPatient") Long cdPatient) {
        PatientModel patient = this.patientService.findById(cdPatient);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

    //Lista o histórico do paciente de maneira paginada pelo ID
    @GetMapping("/historicHospitalization/{cdPatient}")
    public ResponseEntity<Object> getHistoryHospitalizationInfo(
            @PathVariable("cdPatient") Long cdPatient, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.patientService.findHistoryHospitalization(cdPatient, pageable));
    }

    //Seleciona as informação de internação de um paciente pelo ID
    @GetMapping("/patientsHospitalization/{cdPatient}")
    public ResponseEntity<Object> getPatientHospitalizationInfo(@PathVariable(value="cdPatient") Long cdPatient) {
        return ResponseEntity.ok(this.patientService.findPatientHospitalizationInfo(cdPatient));
    }

    //Atualiza uma paciente pelo ID
    @PutMapping("/patients/{cdPatient}")
    public ResponseEntity<Object> updatepatient(@PathVariable(value="cdPatient") long cdPatient,
                                               @RequestBody @Valid PatientDTO patientDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.patientService.update(cdPatient, patientDTO));
    }

    //Deleta uma paciente pelo ID
    @DeleteMapping("/patients/{cdPatient}")
    public ResponseEntity<Object> deletepatient(@PathVariable(value="cdPatient") long cdPatient) {
        return this.patientService.delete(cdPatient);
    }

    //Interna uma paciente.
    @PostMapping("/hospitalizations/{cdPatient}/{cdSpecialty}")
    public ResponseEntity<Object> hospitalizationPacient(@PathVariable(value="cdPatient") Long cdPatient,
                                                         @PathVariable(value="cdSpecialty") int cdSpecialty) {
        return this.patientService.hospitalizationPaciente(cdPatient, cdSpecialty);
    }

    //Da alta para um paciente.
    @PutMapping("/releasePatient/{cdHospitalization}")
    public ResponseEntity<Object>  releasePatient(@PathVariable(value="cdPatient") Long cdHospitalization) {
        return ResponseEntity.status(HttpStatus.OK).body(this.patientService.releasePatient(cdHospitalization));
    }
}
