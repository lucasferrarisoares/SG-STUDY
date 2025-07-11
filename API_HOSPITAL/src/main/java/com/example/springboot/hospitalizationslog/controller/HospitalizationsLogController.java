package com.example.springboot.hospitalizationslog.controller;

import com.example.springboot.hospitalizationslog.DTO.HospitalizationActiveDTO;
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
import java.util.stream.Stream;

@RestController
public class HospitalizationsLogController {
    @Autowired private HospitalizationsLogService hospitalizationsLogService;

    //Salva uma internação
    @PostMapping("/HospitalizationsLogs")
    public ResponseEntity<HospitalizationsLogModel> saveHospitalizationsLog(@RequestBody @Valid HospitalizationsLogDTO hospitalizationsLogDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.hospitalizationsLogService.save(hospitalizationsLogDTO));
    }

    //Lista todas as internações
    @GetMapping("/hospitalizationsLogs")
    public ResponseEntity<List<HospitalizationsLogModel>> getAllhospitalizationsLogs() {
        return ResponseEntity.status(HttpStatus.OK).body(this.hospitalizationsLogService.listAll());
    }

    //Lista as intenações ativas.
    @GetMapping("/hospitalizationsActive")
    public ResponseEntity<Stream<HospitalizationActiveDTO>> getActiveHospitalizations() {
        return ResponseEntity.status(HttpStatus.OK).body(this.hospitalizationsLogService.listActiveHospitalizations());
    }

    //Pega uma Internação
    @GetMapping("/hospitalizationsLogs/{cdHospitalizationsLog}")
    public ResponseEntity<Object> getOneHospitalizationsLog(@PathVariable(value="cdHospitalizationsLog") Long cdHospitalizationsLog) {
        return ResponseEntity.status(HttpStatus.OK).body(this.hospitalizationsLogService.findById(cdHospitalizationsLog));
    }

    //Atualiza uma log de internação
    @PutMapping("/hospitalizationsLogs/{cdHospitalizationsLog}")
    public ResponseEntity<Object> updateHospitalizationsLog(@PathVariable(value="cdHospitalizationsLog") long cdHospitalizationsLog,
                                               @RequestBody @Valid HospitalizationsLogDTO hospitalizationsLogDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.hospitalizationsLogService.update(cdHospitalizationsLog, hospitalizationsLogDTO));
    }

    //Deleta uma log de internação.
    @DeleteMapping("/hospitalizationsLogs/{cdHospitalizationsLog}")
    public ResponseEntity<Object> deleteHospitalizationsLog(@PathVariable(value="cdHospitalizationsLog") long cdHospitalizationsLog) {
        return this.hospitalizationsLogService.delete(cdHospitalizationsLog);
    }
}
