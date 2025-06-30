package com.example.springboot.patient.service;

import com.example.springboot.patient.projection.PatientHistoryProjection;
import com.example.springboot.bed.model.BedModel;
import com.example.springboot.bed.service.BedService;
import com.example.springboot.enumerated.specialty.Specialty;
import com.example.springboot.enumerated.status.Status;
import com.example.springboot.hospitalizationslog.DTO.HospitalizationsLogDTO;
import com.example.springboot.hospitalizationslog.model.HospitalizationsLogModel;
import com.example.springboot.hospitalizationslog.service.HospitalizationsLogService;
import com.example.springboot.patient.DTO.PatientDTO;
import com.example.springboot.patient.DTO.PatientHistoryDTO;
import com.example.springboot.patient.DTO.PatientHospitalizationDTO;
import com.example.springboot.patient.model.PatientModel;
import com.example.springboot.patient.projection.PacientHospitalizationProjection;
import com.example.springboot.patient.repository.PatientRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return this.patientRepository.findAll();
    }

    public PatientModel save(@RequestBody @Valid PatientDTO patientDTO) {
        PatientModel patient = new PatientModel();
        BeanUtils.copyProperties(patientDTO, patient);
        this.patientRepository.save(patient);
        return patient;
    }

    public PatientModel update(@NotNull PatientModel patient) {
        return this.patientRepository.save(patient);
    }

    public void delete(@NotNull PatientModel patient) {
        this.patientRepository.delete(patient);
    }

    public PatientHospitalizationDTO findPatientHospitalizationInfo(Long cdPatient) {
        PacientHospitalizationProjection projection = this.patientRepository.findPatientHospitalizationInfo(cdPatient);
        return new PatientHospitalizationDTO(projection.getHpName(), Specialty.fromcdSpecialty(projection.getSpecialty()), projection.getHWingModel(), projection.getCdRoom(), projection.getPtName(), projection.getDtHospitalization());
    }

    public Object releasePatient(Long cdHospitalization) {
        HospitalizationsLogModel hospitalization = this.hospitalizationsLogService.findHospitalizedByPatient(cdHospitalization);
        hospitalization.setDtDischarge(new Date());

        BedModel bed = this.bedService.findById(hospitalization.getCdBed().getCdBed());
        bed.setCdStatus(Status.CLEANING);
        bed.setCdPatient(null);
        this.bedService.update(bed);
        return this.hospitalizationsLogService.update(hospitalization);
    }

    public ResponseEntity<Object> hospitalizationPaciente(Long cdPatient, int cdSpecialty) {
        BedModel bed = this.bedService.findFreeBedBySpecialty(cdSpecialty);
        if (bed == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem leitos disponíveis");
        }

        if (this.verifyPatientInBed(cdPatient)) {
            return ResponseEntity.status(HttpStatus.SEE_OTHER).body("Paciente já se encontra internado");
        }

        bed.setCdPatient(this.findById(cdPatient));
        bed.setCdStatus(Status.BUSY);
        this.bedService.update(bed);

        HospitalizationsLogDTO hospitalizationsLogDTO = new HospitalizationsLogDTO(Specialty.fromcdSpecialty(cdSpecialty), cdPatient, bed.getCdBed());

        this.hospitalizationsLogService.save(hospitalizationsLogDTO);
        return ResponseEntity.status(HttpStatus.OK).body(hospitalizationsLogDTO);
    }

    public boolean verifyPatientInBed(Long cdPatient) {
        return this.patientRepository.verifyFreeBed(cdPatient);
    }


    public Page<PatientHistoryDTO> findHistoryHospitalization(Long cdPatient, Pageable pageable) {
        Page<PatientHistoryProjection> page = patientRepository.findHistoryHospitalization(cdPatient, pageable);

        return page.map(projection -> new PatientHistoryDTO(
                projection.getPtName(), projection.getDeSpecialty(),
                projection.getDtHospitalization(), projection.getDtDischarg()
        ));
    }
}
