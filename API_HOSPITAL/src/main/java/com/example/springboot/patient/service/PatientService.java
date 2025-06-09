package com.example.springboot.patient.service;

import com.example.springboot.patient.projection.PatientHistoryProjection;
import com.example.springboot.patient.DTO.PatientDTO;
import com.example.springboot.patient.DTO.PatientHistoryDTO;
import com.example.springboot.patient.model.PatientModel;
import com.example.springboot.patient.repository.PatientRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

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


    public Page<PatientHistoryDTO> findHistoryHospitalization(Long cdPatient, Pageable pageable) {
        Page<PatientHistoryProjection> page = patientRepository.findHistoryHospitalization(cdPatient, pageable);

        return page.map(projection -> new PatientHistoryDTO(
                projection.getPtName(), projection.getDeSpecialty(),
                projection.getDtHospitalization(), projection.getDtDischarg()
        ));
    }
}
