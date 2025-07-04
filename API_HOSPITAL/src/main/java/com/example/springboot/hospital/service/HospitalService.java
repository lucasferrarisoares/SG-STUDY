package com.example.springboot.hospital.service;

import com.example.springboot.hospital.DTO.HospitalDTO;
import com.example.springboot.hospital.model.HospitalModel;
import com.example.springboot.hospital.repository.HospitalRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

@Service
public class HospitalService {

    @Autowired private HospitalRepository hospitalRepository;

    //Encontrar o hospital pelo ID
    @Transactional(readOnly = true)
    public HospitalModel findById(long id) {
        return this.hospitalRepository.findById(id).orElseThrow(() -> new RuntimeException("Hospital não encontrado"));
    }

    //Lista todos os Hospitais
    @Transactional(readOnly = true)
    public List<HospitalModel> listAll() {
        return this.hospitalRepository.findAll();
    }

    //Salva o hospital
    @Transactional
    public HospitalModel save(@RequestBody @Valid HospitalDTO hospitalDTO) {
        HospitalModel hospital = new HospitalModel();
        BeanUtils.copyProperties(hospitalDTO, hospital);
        return this.hospitalRepository.save(hospital);
    }

    //Atualiza o Hospital
    @Transactional
    public HospitalModel update(@NotNull Long cdHospital, HospitalDTO hospitalDTO) {
        HospitalModel hospital = this.findById(cdHospital);
        hospital.setDeHospital(hospitalDTO.deHospital());
        return this.hospitalRepository.save(hospital);
    }

    //Deleta o Hospital
    @Transactional
    public ResponseEntity<Object> delete(@NotNull Long cdHospital) {
        this.hospitalRepository.delete(this.findById(cdHospital));
        return ResponseEntity.ok(Collections.singletonMap("mensagem", "hospital deletado com sucesso"));
    }
}
