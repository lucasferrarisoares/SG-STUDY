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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class HospitalizationsLogService {

    @Autowired private HospitalizationsLogRepository hospitalizationsLogRepository;
    @Autowired private PatientRepository patientRepository;
    @Autowired private BedRepository bedRepository;

    //Seleciona uma log pelo ID
    @Transactional(readOnly = true)
    public HospitalizationsLogModel findById(long id) {
        return this.hospitalizationsLogRepository.findById(id).orElseThrow(() -> new RuntimeException("Hospital não encontrado"));
    }

    //Lista todas as logs
    @Transactional(readOnly = true)
    public List<HospitalizationsLogModel> listAll() {
        return this.hospitalizationsLogRepository.findAll();
    }

    //Salva uma log
    @Transactional
    public HospitalizationsLogModel save(@RequestBody @Valid HospitalizationsLogDTO hospitalizationDTO) {
        HospitalizationsLogModel hospitalization = new HospitalizationsLogModel();
        hospitalization.setDtHospitalization(new Date());
        hospitalization.setCdPatient(patientRepository.findById(hospitalizationDTO.cdPatient()).orElseThrow(() -> new RuntimeException("Paciente não encontrado")));
        hospitalization.setCdBed(bedRepository.findById(hospitalizationDTO.cdBed()).orElseThrow(() -> new RuntimeException("Ala não encontrado")));
        hospitalization.setDeSpecialty(hospitalizationDTO.specialty());
        return this.hospitalizationsLogRepository.save(hospitalization);
    }

    //Atualiza uma log recebendo seu ID e DTO
    @Transactional
    public HospitalizationsLogModel update(@NotNull Long cdHospitalizationsLog, HospitalizationsLogDTO hospitalizationsLogDTO) {
        HospitalizationsLogModel hospitalizationsLog = this.findById(cdHospitalizationsLog);
        BeanUtils.copyProperties(hospitalizationsLogDTO, hospitalizationsLog);
        return this.hospitalizationsLogRepository.save(hospitalizationsLog);
    }

    //Atualiza uma log recebendo seu model.
    @Transactional
    public HospitalizationsLogModel update(@NotNull HospitalizationsLogModel hospitalizationsLogModel) {
        return this.hospitalizationsLogRepository.save(hospitalizationsLogModel);
    }

    //Deleta uma log
    @Transactional
    public ResponseEntity<Object> delete(@NotNull Long cdHospitalizationLog) {
        this.hospitalizationsLogRepository.delete(this.findById(cdHospitalizationLog));
        return ResponseEntity.status(HttpStatus.OK).body("HospitalizationsLog deletado com sucesso");
    }

    //Econtra a log de um paciente
    @Transactional(readOnly = true)
    public HospitalizationsLogModel findHospitalizedByPatient(@NotNull Long cdHospitalization) {
        return this.hospitalizationsLogRepository.findById(cdHospitalization).orElseThrow(null);
    }

    //Lista as internações ativas.
    @Transactional(readOnly = true)
    public Stream<HospitalizationActiveDTO> listActiveHospitalizations() {
        List<HospitalizationProjection> list = this.hospitalizationsLogRepository.listActiveHospitalizations();

        return list.stream().map(projection -> new HospitalizationActiveDTO(
                projection.getDePatient(), Specialty.fromcdSpecialty(projection.getCdSpecialty()),
                projection.getDtHospitalization(), projection.getNuHospitalization()));
    }
}
