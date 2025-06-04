package com.example.springboot.hospital.service;

import com.example.springboot.hospital.DTO.HospitalDTO;
import com.example.springboot.hospital.model.HospitalModel;
import com.example.springboot.hospital.repository.HospitalRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public HospitalModel findById(long id) {
        return hospitalRepository.findById(id).orElseThrow(() -> new RuntimeException("Hospital não encontrado"));
    }
    public List<HospitalModel> listAll() {
        return hospitalRepository.findAll();
    }

    public HospitalModel save(@RequestBody @Valid HospitalDTO hospitalDTO) {
        HospitalModel hospital = new HospitalModel();
        BeanUtils.copyProperties(hospitalDTO, hospital);
        hospitalRepository.save(hospital);
        return hospital;
    }

    public HospitalModel update(@NotNull HospitalModel hospital) {
        return hospitalRepository.save(hospital);
    }

    public void delete(@NotNull HospitalModel hospital) {
        hospitalRepository.delete(hospital);
    }
}
