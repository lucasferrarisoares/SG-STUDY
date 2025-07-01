package com.example.springboot.room.service;

import com.example.springboot.enumerated.specialty.Specialty;
import com.example.springboot.enumerated.status.Status;
import com.example.springboot.hwing.repository.HWingRepository;
import com.example.springboot.room.DTO.RoomDTO;
import com.example.springboot.room.DTO.RoomSpecialtyDTO;
import com.example.springboot.room.DTO.RoomNUDTO;
import com.example.springboot.room.model.RoomModel;
import com.example.springboot.room.projection.RoomProjection;
import com.example.springboot.room.repository.RoomRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired private RoomRepository roomRepository;
    @Autowired private HWingRepository hwingRepository;

    public RoomService(RoomRepository roomRepository, HWingRepository hwingRepository) {
        this.roomRepository = roomRepository;
        this.hwingRepository = hwingRepository;
    }

    //Encontra um quarto pelo ID
    @Transactional(readOnly = true)
    public RoomModel findById(long id) {
        return this.roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room não encontrado"));
    }

    //Diz se o paciente está ou não Internado.
    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByPatient(long cdPatient) {
        RoomModel room = this.roomRepository.findByPatient(cdPatient);
        if (room != null) {
            return ResponseEntity.status(HttpStatus.OK).body(room);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não está internado");
    }

    //Lista todos os quartos
    @Transactional(readOnly = true)
    public List<RoomModel> listAll() {
        return this.roomRepository.findAll();
    }

    //Salva um quarto
    @Transactional
    public RoomModel save(@RequestBody @Valid RoomDTO roomDTO) {
        RoomModel room = new RoomModel();
        room.setCdStatus(Status.fromcdStatus(roomDTO.cdStatus()));
        room.setDeCodigo(roomDTO.deCodigo());
        room.setCdHWing(this.hwingRepository.findById(roomDTO.cdHWing()).orElseThrow(() -> new RuntimeException("Ala não encontrada")));
        room.setNuBed(roomDTO.nuBed());
        return this.roomRepository.save(room);
    }

    //Numera o número de quartos ocupados e livres, para uma determinada especialidade
    @Transactional(readOnly = true)
    public RoomNUDTO nuRoomBySpecialty(Integer cdSpecialty) {
        Long freeBed = this.roomRepository.nuFreeRoomBySpecialty(cdSpecialty);
        Long busyBed = this.roomRepository.nuBusyRoomBySpecialty(cdSpecialty);
        return new RoomNUDTO(Specialty.fromcdSpecialty(cdSpecialty), freeBed, busyBed, freeBed + busyBed);
    }

    //Atualiza um quarto
    @Transactional
    public RoomModel update(@NotNull Long cdRoom, RoomDTO roomDTO) {
        RoomModel room = this.findById(cdRoom);
        BeanUtils.copyProperties(roomDTO, room);
        return this.roomRepository.save(room);
    }

    //Deleta uma quarto
    @Transactional
    public ResponseEntity<Object> delete(@NotNull Long cdRoom) {
        this.roomRepository.delete(this.findById(cdRoom));
        return ResponseEntity.status(HttpStatus.OK).body("Quarto deletado com sucesso");
    }

    //Verifica se um quarto está ou não ocupado
    @Transactional(readOnly = true)
    public void verifyRoomIsFree(Long cdRoom) {
        if (!(this.roomRepository.verifyRoomIsFree(cdRoom))) {
            RoomModel room = this.findById(cdRoom);
            room.setCdStatus(Status.BUSY);
            this.roomRepository.save(room);
        }
    }

    //Lista os quartos livres
    @Transactional(readOnly = true)
    public List<RoomSpecialtyDTO> listFreeRoom() {
        List<RoomProjection> projections = this.roomRepository.listFreeRoom();

        return projections.stream()
                .map(projection -> new RoomSpecialtyDTO(projection.getDeCode(), Specialty.fromcdSpecialty(projection.getCdSpecialty())))
                .collect(Collectors.toList());
    }
}
