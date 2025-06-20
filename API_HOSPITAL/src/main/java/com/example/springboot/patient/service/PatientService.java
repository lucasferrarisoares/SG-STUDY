package com.example.springboot.patient.service;

import com.example.springboot.bed.model.BedModel;
import com.example.springboot.bed.service.BedService;
import com.example.springboot.enumerated.status.Status;
import com.example.springboot.hospitalizationslog.model.HospitalizationsLogModel;
import com.example.springboot.hospitalizationslog.service.HospitalizationsLogService;
import com.example.springboot.patient.DTO.PatientDTO;
import com.example.springboot.patient.model.PatientModel;
import com.example.springboot.patient.repository.PatientRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private BedService bedService;
    @Autowired
    private HospitalizationsLogService hospitalizationsLogService;

    public PatientModel findById(long id) {
        return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient não encontrado"));
    }
    public List<PatientModel> listAll() {
        return patientRepository.findAll();
    }

    public PatientModel save(@RequestBody @Valid PatientDTO patientDTO) {
        PatientModel patient = new PatientModel();
        BeanUtils.copyProperties(patientDTO, patient);
        patientRepository.save(patient);
        return patient;
    }

    public PatientModel update(@NotNull PatientModel patient) {
        return patientRepository.save(patient);
    }

    public void delete(@NotNull PatientModel patient) {
        patientRepository.delete(patient);
    }

    public Object releasePatient(Long cdHospitalization) {
        HospitalizationsLogModel hospitalization = hospitalizationsLogService.findHospitalizedByPatient(cdHospitalization);
        hospitalization.setDtDischarge(new Date());

        BedModel bed = bedService.findById(hospitalization.getCdBed().getCdBed());
        bed.setCdStatus(Status.CLEANING);
        bed.setCdPatient(null);
        bedService.update(bed);
        return this.hospitalizationsLogService.update(hospitalization);
    }
}
