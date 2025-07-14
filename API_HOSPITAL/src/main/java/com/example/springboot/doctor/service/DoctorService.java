package com.example.springboot.doctor.service;

import com.example.springboot.doctor.model.DoctorModel;
import com.example.springboot.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {

    @Autowired private DoctorRepository doctorRepository;

    @Transactional
    public DoctorModel saveDoctor(DoctorModel doctor){
        return this.doctorRepository.save(doctor);
    }

    @Transactional(readOnly = true)
    public List<DoctorModel> listDoctors(){
        return this.doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public DoctorModel findById(Long id){
        return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Médico não encontrado."));
    }

    @Transactional
    public DoctorModel updateDoctor(Long id, DoctorModel updateddoctor){
        DoctorModel doctor = this.findById(id);
        doctor.setDeName(updateddoctor.getDeName());
        doctor.setCdSpecialty(updateddoctor.getCdSpecialty());
        doctor.setNuCRM(updateddoctor.getNuCRM());
        return this.doctorRepository.save(doctor);
    }

    @Transactional
    public void deleteDoctor(Long id){
        this.doctorRepository.deleteById(id);
    }

    public DoctorModel findDoctorByCRM(Long crm) {
        return this.doctorRepository.findDoctorByCRM(crm).orElseThrow(() -> new RuntimeException("CRM não encontrado."));
    }
}
