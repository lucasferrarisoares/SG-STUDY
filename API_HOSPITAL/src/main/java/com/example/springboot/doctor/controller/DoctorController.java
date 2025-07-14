package com.example.springboot.doctor.controller;

import com.example.springboot.doctor.model.DoctorModel;
import com.example.springboot.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired private DoctorService doctorService;

    @PostMapping("/doctors")
    public ResponseEntity<DoctorModel> saveDoctor(@RequestBody DoctorModel doctor){
        return ResponseEntity.ok(this.doctorService.saveDoctor(doctor));
    }

    @PutMapping("/doctors/{id}")
    public ResponseEntity<DoctorModel> updateDoctor(@PathVariable Long id, @RequestBody DoctorModel doctor){
        return ResponseEntity.ok(this.doctorService.updateDoctor(id, doctor));
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorModel>> listAlldoctors(){
        return ResponseEntity.ok(this.doctorService.listDoctors());
    }

    @GetMapping("/doctors/crm/{crm}")
    public ResponseEntity<DoctorModel> findDoctorByCRM(@PathVariable Long crm){
        return ResponseEntity.ok(this.doctorService.findDoctorByCRM(crm));
    }

    @DeleteMapping("/doctors/{id}")
    public void deleteDoctor(@PathVariable Long id){
        this.doctorService.deleteDoctor(id);
    }
}
