package com.example.springboot.bed.service;

import com.example.springboot.bed.DTO.BedDTO;
import com.example.springboot.bed.model.BedModel;
import com.example.springboot.bed.repository.BedRepository;
import com.example.springboot.enumerated.status.Status;
import com.example.springboot.room.service.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BedService {

    @Autowired private BedRepository bedRepository;
    @Autowired private RoomService roomService;

    //Encontra cama pelo ID
    @Transactional(readOnly = true)
    public BedModel findById(long cdBed) {
        return this.bedRepository.findById(cdBed).orElseThrow(() -> new RuntimeException("Leito não encontrado"));
    }

    //Lista todas as camas
    @Transactional(readOnly = true)
    public List<BedModel> listAll() {
        return this.bedRepository.findAll();
    }

    //Salva camas.
    @Transactional
    public BedModel save(@RequestBody @Valid BedDTO bedDTO) {
        BedModel bed = new BedModel();
        bed.setDeCode(bedDTO.deCodigo());
        bed.setCdStatus(Status.FREE);
        bed.setCdRoom(this.roomService.findById(bedDTO.cdRoom()));

//        //Função que garante a verificação de existência do paciênte caso ele seja passado.
//        if (bedDTO.cdPatient() != null) {
//            bed.setCdPatient(this.patientRepository.findById(bedDTO.cdPatient()).orElseThrow(() -> new RuntimeException("Paciente não encontrado")));
//        }
        return this.bedRepository.save(bed);
    }

    //Atualiza a cama, recebendo seu ID e DTO.
    @Transactional
    public BedModel update(@NotNull Long cdBed, BedDTO bedDTO) {
        BedModel bed = this.findById(cdBed);
        BeanUtils.copyProperties(bedDTO, bed);
        return this.bedRepository.save(bed);
    }

    //Atualiza uma cama recebendo seu model.
    @Transactional
    public BedModel update(@NotNull BedModel updateBed) {
        BedModel bed = bedRepository.save(updateBed);
        this.roomService.verifyRoomIsFree(bed.getCdRoom());
        return bed;
    }

    //Deleta a cama
    @Transactional
    public String delete(@NotNull BedModel bed) {
        this.bedRepository.delete(bed);
        return "Cama deletada com sucesso";
    }

    //Encontra uma cama livre de determinada especialidade.
    @Transactional(readOnly = true)
    public BedModel findFreeBedBySpecialty(@NotNull Integer cdSpecialty) {
        return this.bedRepository.findFreeBedBySpecialty(cdSpecialty);
    }

    //Encontra a cama na qual um paciente está internado.
    @Transactional(readOnly = true)
    public BedModel findByPatient(@NotNull Long cdPacient) {
        return this.bedRepository.findByPatient(cdPacient);
    }

    //Exibe a log de internações de uma cama
    @Transactional(readOnly = true)
    public Object findHospitalizationLogByBed(Long cdBed) {
        return this.bedRepository.findHospitalizationLogByBed(cdBed);
    }

    //Recebe o quarto que está em limpeza e retorna como limpo.
    @Transactional
    public Object finishCleaning(Long cdBed) {
        BedModel bed = this.findById(cdBed);
        bed.setCdStatus(Status.FREE);

        //FUNÇÃO PARA COLOCAR O QUARTO COMO LIVRE.
        this.roomService.verifyRoomIsFree(bed.getCdRoom());
        return this.bedRepository.save(bed);
    }

    //List todos os quartos de uma especialidade.
    @Transactional(readOnly = true)
    public List<BedModel> findBySpecialty(Integer cdSpecialty) {
        return this.bedRepository.findBySpecialty(cdSpecialty);
    }
}
