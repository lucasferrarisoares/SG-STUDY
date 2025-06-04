package com.example.springboot.bed.service;

import com.example.springboot.bed.DTO.BedDTO;
import com.example.springboot.bed.model.BedModel;
import com.example.springboot.bed.repository.BedRepository;
import com.example.springboot.enumerated.status.Status;
import com.example.springboot.hwing.model.HWingModel;
import com.example.springboot.patient.repository.PatientRepository;
import com.example.springboot.room.repository.RoomRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BedService {

    @Autowired
    private BedRepository bedRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PatientRepository patientRepository;

    public BedModel findById(long id) {
        return bedRepository.findById(id).orElseThrow(() -> new RuntimeException("Leito não encontrado"));
    }

    public List<BedModel> listAll() {
        return bedRepository.findAll();
    }

    public BedModel save(@RequestBody @Valid BedDTO bedDTO) {
        BedModel bed = new BedModel();
        bed.setDeCode(bedDTO.deCodigo());
        bed.setCdStatus(Status.FREE);
        bed.setCdRoom(roomRepository.findById(bedDTO.cdRoom()).orElseThrow(() -> new RuntimeException("Quarto não encontrado")));

        if (bedDTO.cdPatient() != null) {
            bed.setCdPatient(patientRepository.findById(bedDTO.cdPatient()).orElseThrow(() -> new RuntimeException("Paciente não encontrado")));
        }
        return bedRepository.save(bed);
    }

    public BedModel update(@NotNull BedModel bed) {
        return bedRepository.save(bed);
    }

    public void delete(@NotNull BedModel bed) {
        bedRepository.delete(bed);
    }

    public BedModel findFreeBedBySpecialty(@NotNull Integer cdSpecialty) {
        return bedRepository.findFreeBedBySpecialty(cdSpecialty);
    }

    public BedModel findByiInpatient(@NotNull Long cdPacient) {
        return bedRepository.findByiInpatient(cdPacient);
    }
}
