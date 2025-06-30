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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HWingRepository hwingRepository;

    public RoomService(RoomRepository roomRepository, HWingRepository hwingRepository) {
        this.roomRepository = roomRepository;
        this.hwingRepository = hwingRepository;
    }

    public RoomModel findById(long id) {
        return this.roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room não encontrado"));
    }

    public RoomModel findByPatient(long cdPatient) {
        return roomRepository.findByPatient(cdPatient);
    }

    public List<RoomModel> listAll() {
        return this.roomRepository.findAll();
    }

    public RoomModel save(@RequestBody @Valid RoomDTO roomDTO) {
        RoomModel room = new RoomModel();
        room.setCdStatus(Status.fromcdStatus(roomDTO.cdStatus()));
        room.setDeCodigo(roomDTO.deCodigo());
        room.setCdHWing(this.hwingRepository.findById(roomDTO.cdHWing()).orElseThrow(() -> new RuntimeException("Ala não encontrado")));
        return this.roomRepository.save(room);
    }

    public RoomNUDTO nuRoomBySpecialty(Integer cdSpecialty) {
        Long freeBed = roomRepository.nuFreeRoomBySpecialty(cdSpecialty);
        Long busyBed = roomRepository.nuBusyRoomBySpecialty(cdSpecialty);
        return new RoomNUDTO(Specialty.fromcdSpecialty(cdSpecialty), freeBed, busyBed, freeBed + busyBed);
    }

    public RoomModel update(@NotNull RoomModel room) {
        return this.roomRepository.save(room);
    }

    public void delete(@NotNull RoomModel room) {
        this.roomRepository.delete(room);
    }

    public List<RoomSpecialtyDTO> listFreeRoom() {
        List<RoomProjection> projections = roomRepository.listFreeRoom();

        return projections.stream()
                .map(projection -> new RoomSpecialtyDTO(projection.getDeCode(), Specialty.fromcdSpecialty(projection.getCdSpecialty())))
                .collect(Collectors.toList());
    }
}
