package com.example.springboot.hospitalizationslog.controller;

import com.example.springboot.hospitalizationslog.DTO.HospitalizationsLogDTO;
import com.example.springboot.hospitalizationslog.model.HospitalizationsLogModel;
import com.example.springboot.hospitalizationslog.service.HospitalizationsLogService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HospitalizationsLogController {
    @Autowired
    private HospitalizationsLogService hospitalizationsLogService;

    @PostMapping("/HospitalizationsLogs")
    public ResponseEntity<HospitalizationsLogModel> saveHospitalizationsLog(@RequestBody @Valid HospitalizationsLogDTO hospitalizationsLogDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalizationsLogService.save(hospitalizationsLogDTO));
    }

    @GetMapping("/hospitalizationsLogs")
    public ResponseEntity<List<HospitalizationsLogModel>> getAllhospitalizationsLogs() {
        return ResponseEntity.status(HttpStatus.OK).body(hospitalizationsLogService.listAll());
    }

    @GetMapping("/hospitalizationsLogs/{cdHospitalizationsLog}")
    public ResponseEntity<Object> getOneHospitalizationsLog(@PathVariable(value="cdHospitalizationsLog") Long cdHospitalizationsLog) {
        HospitalizationsLogModel hospitalizationsLog = hospitalizationsLogService.findById(cdHospitalizationsLog);
        return ResponseEntity.status(HttpStatus.OK).body(hospitalizationsLog);
    }

    @PutMapping("/hospitalizationsLogs/{cdHospitalizationsLog}")
    public ResponseEntity<Object> updateHospitalizationsLog(@PathVariable(value="cdHospitalizationsLog") long cdHospitalizationsLog,
                                               @RequestBody @Valid HospitalizationsLogDTO hospitalizationsLogDTO) {
        HospitalizationsLogModel hospitalizationsLog = hospitalizationsLogService.findById(cdHospitalizationsLog);
        BeanUtils.copyProperties(hospitalizationsLogDTO, hospitalizationsLog);
        return ResponseEntity.status(HttpStatus.OK).body(hospitalizationsLogService.update(hospitalizationsLog));
    }

    @DeleteMapping("/hospitalizationsLogs/{cdHospitalizationsLog}")
    public ResponseEntity<Object> deleteHospitalizationsLog(@PathVariable(value="cdHospitalizationsLog") long cdHospitalizationsLog) {
        HospitalizationsLogModel hospitalizationsLog = hospitalizationsLogService.findById(cdHospitalizationsLog);
        hospitalizationsLogService.delete(hospitalizationsLog);
        return ResponseEntity.status(HttpStatus.OK).body("HospitalIzationsLog deletado com sucesso");
    }
}
