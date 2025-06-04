package com.example.springboot.room.service;

import com.example.springboot.enumerated.specialty.Specialty;
import com.example.springboot.enumerated.status.Status;
import com.example.springboot.hwing.repository.HWingRepository;
import com.example.springboot.room.DTO.RoomDTO;
import com.example.springboot.room.model.RoomModel;
import com.example.springboot.room.repository.RoomRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room não encontrado"));
    }
    public List<RoomModel> listAll() {
        return roomRepository.findAll();
    }

    public RoomModel save(@RequestBody @Valid RoomDTO roomDTO) {
        RoomModel room = new RoomModel();
        room.setCdStatus(Status.fromcdStatus(roomDTO.cdStatus()));
        room.setDeCodigo(roomDTO.deCodigo());
        room.setCdHWing(hwingRepository.findById(roomDTO.cdHWing()).orElseThrow(() -> new RuntimeException("Ala não encontrado")));
        return roomRepository.save(room);
    }

    public RoomModel update(@NotNull RoomModel room) {
        return roomRepository.save(room);
    }

    public void delete(@NotNull RoomModel room) {
        roomRepository.delete(room);
    }
}
