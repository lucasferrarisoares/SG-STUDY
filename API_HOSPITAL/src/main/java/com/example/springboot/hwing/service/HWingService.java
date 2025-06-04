package com.example.springboot.hwing.service;

import com.example.springboot.enumerated.specialty.Specialty;
import com.example.springboot.hospital.repository.HospitalRepository;
import com.example.springboot.hwing.DTO.HWingDTO;
import com.example.springboot.hwing.model.HWingModel;
import com.example.springboot.hwing.repository.HWingRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class HWingService {

    @Autowired
    private HWingRepository hwingRepository;
    @Autowired
    private HospitalRepository hospitalRepository;

    private HWingService(HWingRepository hwingRepository, HospitalRepository hospitalRepository ) {

    }

    public HWingModel findById(long id) {
        return hwingRepository.findById(id).orElseThrow(() -> new RuntimeException("hWing não encontrado"));
    }
    public List<HWingModel> listAll() {
        return hwingRepository.findAll();
    }

    public HWingModel save(@RequestBody @Valid HWingDTO hwingDTO) {
        HWingModel hwing = new HWingModel();
        BeanUtils.copyProperties(hwingDTO, hwing);
        hwing.setCdHospital(hospitalRepository.findById(hwingDTO.cdHospital()).orElseThrow(() -> new RuntimeException("Hospital não encontrado")));
        hwing.setDeSpecialty(Specialty.fromcdSpecialty(hwingDTO.cdSpecialty()));
        hwingRepository.save(hwing);
        return hwing;
    }

    public HWingModel update(@NotNull HWingModel hwing) {
        return hwingRepository.save(hwing);
    }

    public void delete(@NotNull HWingModel hwing) {
        hwingRepository.delete(hwing);
    }
}
