package com.example.springboot.patient.service;

import com.example.springboot.patient.DTO.PageResponseDTO;
import com.example.springboot.patient.projection.PatientHistoryProjection;
import com.example.springboot.bed.model.BedModel;
import com.example.springboot.bed.service.BedService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import static com.example.springboot.enumerated.specialty.Specialty.fromcdSpecialty;

import java.util.Date;
import java.util.List;

@Service
public class PatientService {

    @Autowired private PatientRepository patientRepository;
    @Autowired private BedService bedService;
    @Autowired private HospitalizationsLogService hospitalizationsLogService;


    //Encontra um Paciente pelo ID
    @Transactional(readOnly = true)
    public PatientModel findById(long id) {
        return this.patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient não encontrado"));
    }

    //Lista todos os pacientes
    @Transactional(readOnly = true)
    public List<PatientModel> listAll() {
        return this.patientRepository.findAll();
    }

    //Salva um paciente.
    @Transactional
    public PatientModel save(@RequestBody @Valid PatientDTO patientDTO) {
        PatientModel patient = new PatientModel();
        BeanUtils.copyProperties(patientDTO, patient);
        return this.patientRepository.save(patient);
    }

    //Atualiza um paciente pelo ID e DTO.
    @Transactional
    public PatientModel update(@NotNull Long cdPatient, PatientDTO patientDTO) {
        PatientModel patient = this.findById(cdPatient);
        BeanUtils.copyProperties(patientDTO, patient);
        return this.patientRepository.save(patient);
    }

    //Deleta um Paciente
    @Transactional
    public ResponseEntity<Object> delete(@NotNull Long cdPatient) {
        this.patientRepository.delete(this.findById(cdPatient));
        return ResponseEntity.status(HttpStatus.OK).body("patient deletado com sucesso");
    }

    //Encontra as informações de internação de um paciente
    @Transactional(readOnly = true)
    public PatientHospitalizationDTO findPatientHospitalizationInfo(Long cdPatient) {
        PacientHospitalizationProjection projection = this.patientRepository.findPatientHospitalizationInfo(cdPatient);
        return new PatientHospitalizationDTO(projection.getCdHospitalization() ,projection.getHpName(), fromcdSpecialty(projection.getSpecialty()), projection.getHWingModel(), projection.getCdRoom(), projection.getPtName(), projection.getDtHospitalization());
    }

    //Libera um paciente de sua internação
    @Transactional
    public Object releasePatient(Long cdHospitalization) {
        //Define data de alta do paciente
        HospitalizationsLogModel hospitalization = this.hospitalizationsLogService.findHospitalizedByPatient(cdHospitalization);
        hospitalization.setDtDischarge(new Date());

        //Limpa o cd paciente da cama e define ela como em limpeza.
        BedModel bed = this.bedService.findById(hospitalization.getCdBed().getCdBed());
        bed.setCdStatus(Status.CLEANING);
        bed.setCdPatient(null);
        this.bedService.update(bed);

        //Retorna os dados da internação atualizados.
        return this.hospitalizationsLogService.update(hospitalization);
    }

    //Interna uma paciente.
    @Transactional
    public ResponseEntity<Object> hospitalizationPaciente(Long cdPatient, int cdSpecialty) {
        BedModel bed = this.bedService.findFreeBedBySpecialty(cdSpecialty);
        //Verifica se existe a cama desejada;
        if (bed == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem leitos disponíveis");
        }
        //Verifica se o paciente já não está internado.
        if (this.verifyHospitalizationByPatient(cdPatient)) {
            return ResponseEntity.status(HttpStatus.SEE_OTHER).body("Paciente já se encontra internado");
        }

        //Coloca o paciente na cama, e define a cama como OCUPADA.
        bed.setCdPatient(this.findById(cdPatient));
        bed.setCdStatus(Status.BUSY);
        this.bedService.update(bed);

        //Cria a log de internação.
        HospitalizationsLogDTO hospitalizationsLogDTO = new HospitalizationsLogDTO(fromcdSpecialty(cdSpecialty), cdPatient, bed.getCdBed());

        this.hospitalizationsLogService.save(hospitalizationsLogDTO);
        return ResponseEntity.status(HttpStatus.OK).body(hospitalizationsLogDTO);
    }

    //Devolve se o paciente está ou não internado.
    @Transactional(readOnly = true)
    public boolean verifyHospitalizationByPatient(Long cdPatient) {
        return this.patientRepository.verifyHospitalizationByPatient(cdPatient);
    }

    //Devolve o histórico de internação de um paciente de maneira paginada.
    @Transactional(readOnly = true)
    public PageResponseDTO<PatientHistoryDTO> findHistoryHospitalization(Long cdPatient, Pageable pageable) {
        Page<PatientHistoryProjection> page = this.patientRepository.findHistoryHospitalization(cdPatient, pageable);

        List<PatientHistoryDTO> dtoList = page.getContent().stream()
                .map(projection -> new PatientHistoryDTO(projection.getPtName(), projection.getDeSpecialty(),
                        projection.getDtHospitalization(), projection.getDtDischarge())).toList();

        return new PageResponseDTO<>(dtoList, page.getNumber(), page.getSize(), page.getTotalElements(), page.isLast());
    }
}
