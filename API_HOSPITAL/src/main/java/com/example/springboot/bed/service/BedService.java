package com.example.springboot.bed.service;

import com.example.springboot.bed.DTO.BedDTO;
import com.example.springboot.bed.model.BedModel;
import com.example.springboot.bed.repository.BedRepository;
import com.example.springboot.enumerated.status.Status;
import com.example.springboot.patient.repository.PatientRepository;
import com.example.springboot.room.repository.RoomRepository;
import com.example.springboot.room.service.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BedService {

    @Autowired
    private BedRepository bedRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private PatientRepository patientRepository;

    public BedModel findById(long id) {
        return this.bedRepository.findById(id).orElseThrow(() -> new RuntimeException("Leito não encontrado"));
    }

    public List<BedModel> listAll() {
        return this.bedRepository.findAll();
    }

    public BedModel save(@RequestBody @Valid BedDTO bedDTO) {
        BedModel bed = new BedModel();
        bed.setDeCode(bedDTO.deCodigo());
        bed.setCdStatus(Status.FREE);
        bed.setCdRoom(this.roomService.findById(bedDTO.cdRoom()));

        if (bedDTO.cdPatient() != null) {
            bed.setCdPatient(this.patientRepository.findById(bedDTO.cdPatient()).orElseThrow(() -> new RuntimeException("Paciente não encontrado")));
        }
        return this.bedRepository.save(bed);
    }

    public BedModel update(@NotNull BedModel bed) {
        this.roomService.verifyRoomIsFree(bed.getCdRoom().getCdRoom());
        return this.bedRepository.save(bed);
    }

    public void delete(@NotNull BedModel bed) {
        this.bedRepository.delete(bed);
    }

    public BedModel findFreeBedBySpecialty(@NotNull Integer cdSpecialty) {
        return this.bedRepository.findFreeBedBySpecialty(cdSpecialty);
    }

    public BedModel findByPatient(@NotNull Long cdPacient) {
        return this.bedRepository.findByPatient(cdPacient);
    }

    public Object findHospitalizationLogByBed(Long cdBed) {
        return this.bedRepository.findHospitalizationLogByBed(cdBed);
    }

    public Object finishCleaning(Long cdBed) {
        BedModel bed = this.findById(cdBed);
        bed.setCdStatus(Status.FREE);
        //FUNÇÃO PARA COLOCAR O QUARTO COMO LIVRE.
        return this.update(bed);
    }
}
