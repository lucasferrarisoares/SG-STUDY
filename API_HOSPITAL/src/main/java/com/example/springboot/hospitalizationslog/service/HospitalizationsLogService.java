package com.example.springboot.hospitalizationslog.service;

import com.example.springboot.enumerated.specialty.Specialty;
import com.example.springboot.hospitalizationslog.DTO.HospitalizationActiveDTO;
import com.example.springboot.bed.repository.BedRepository;
import com.example.springboot.hospitalizationslog.DTO.HospitalizationsLogDTO;
import com.example.springboot.hospitalizationslog.model.HospitalizationsLogModel;
import com.example.springboot.hospitalizationslog.projection.HospitalizationProjection;
import com.example.springboot.hospitalizationslog.repository.HospitalizationsLogRepository;
import com.example.springboot.patient.repository.PatientRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class HospitalizationsLogService {

    @Autowired
    private HospitalizationsLogRepository hospitalRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private BedRepository bedRepository;

    public HospitalizationsLogModel findById(long id) {
        return hospitalRepository.findById(id).orElseThrow(() -> new RuntimeException("Hospital não encontrado"));
    }
    public List<HospitalizationsLogModel> listAll() {
        return hospitalRepository.findAll();
    }

    public HospitalizationsLogModel save(@RequestBody @Valid HospitalizationsLogDTO hospitalizationDTO) {
        HospitalizationsLogModel hospitalization = new HospitalizationsLogModel();
        hospitalization.setDtHospitalization(new Date());
        hospitalization.setCdPatient(patientRepository.findById(hospitalizationDTO.cdPatient()).orElseThrow(() -> new RuntimeException("Paciente não encontrado")));
        hospitalization.setCdBed(bedRepository.findById(hospitalizationDTO.cdBed()).orElseThrow(() -> new RuntimeException("Ala não encontrado")));
        hospitalization.setDeSpecialty(hospitalizationDTO.specialty());
        hospitalRepository.save(hospitalization);
        return hospitalization;
    }

    public HospitalizationsLogModel update(@NotNull HospitalizationsLogModel hospital) {
        return hospitalRepository.save(hospital);
    }

    public void delete(@NotNull HospitalizationsLogModel hospital) {
        hospitalRepository.delete(hospital);
    }

    public HospitalizationsLogModel findHospitalizedByPatient(@NotNull Long cdHospitalization) {
        return hospitalRepository.findById(cdHospitalization).orElseThrow(null);
    }

    public Stream<HospitalizationActiveDTO> listActiveHospitalizations() {
        List<HospitalizationProjection> list = hospitalRepository.listActiveHospitalizations();

        return list.stream().map(projection -> new HospitalizationActiveDTO(
                projection.getDePatient(), Specialty.fromcdSpecialty(projection.getCdSpecialty()),
                projection.getDtHospitalization(), projection.getNuHospitalization()));
    }
}
