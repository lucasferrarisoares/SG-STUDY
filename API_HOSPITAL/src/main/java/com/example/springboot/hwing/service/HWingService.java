package com.example.springboot.hwing.service;

import com.example.springboot.bed.DTO.BedDTO;
import com.example.springboot.bed.service.BedService;
import com.example.springboot.enumerated.specialty.Specialty;
import com.example.springboot.hospital.service.HospitalService;
import com.example.springboot.hwing.DTO.HWingDTO;
import com.example.springboot.hwing.model.HWingModel;
import com.example.springboot.hwing.repository.HWingRepository;
import com.example.springboot.room.DTO.RoomDTO;
import com.example.springboot.room.model.RoomModel;
import com.example.springboot.room.service.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Service
public class HWingService {

    @Autowired private HWingRepository hwingRepository;
    @Autowired private HospitalService hospitalService;
    @Autowired private RoomService roomService;
    @Autowired private BedService bedService;

    public HWingService(HWingRepository hwingRepository, HospitalService hospitalService, RoomService roomService ) {}

    //Encontra a ala por ID
    @Transactional(readOnly = true)
    public HWingModel findById(long id) {
        return this.hwingRepository.findById(id).orElseThrow(() -> new RuntimeException("hWing não encontrado"));
    }

    //Lista todas as alas
    @Transactional(readOnly = true)
    public List<HWingModel> listAll() {
        return this.hwingRepository.findAll();
    }

    //Salva uma ala
    @Transactional
    public HWingModel save(@RequestBody @Valid HWingDTO hwingDTO) {
        HWingModel hwing = new HWingModel();
        hwing.setCdHospital(hospitalService.findById(hwingDTO.cdHospital()));
        hwing.setDeSpecialty(Specialty.fromcdSpecialty(hwingDTO.cdSpecialty()));
        hwing.setNuRoom(hwingDTO.nuRoom());

        HWingModel hWingModel = this.hwingRepository.save(hwing);
        //Função que cria os quartos ligados a está ala

        this.generateRoom(hWingModel, hwingDTO.nuBed());
        return hWingModel;
    }

    //Salva uma ala
    @Transactional
    public HWingModel update(@NotNull long cdHwing, HWingDTO hWingDTO) {
        HWingModel hwing = this.findById(cdHwing);
        BeanUtils.copyProperties(hWingDTO, hwing);
        return this.hwingRepository.save(hwing);
    }

    //Deleta uma ala
    @Transactional
    public ResponseEntity<Object> delete(@NotNull Long cdHwing) {
        this.hwingRepository.delete(this.findById(cdHwing));
        return ResponseEntity.status(HttpStatus.OK).body("hwing deletado com sucesso");
    }

    //Cria os quartos de cada ala.
    @Transactional
    public void generateRoom(HWingModel wing, int nuBed) {
        for (int i = 1; i <= wing.getNuRoom(); i++) {
            RoomDTO roomDTO = new RoomDTO(wing.getDeSpecialty().getDeName().substring(0,3) + i, 0, wing.getCdHWing(), nuBed);
            RoomModel room = this.roomService.save(roomDTO);
            generateBed(room);
        }
    }

    //Cria as camas ligadas aos quartos
    @Transactional
    public void generateBed(RoomModel room) {
        for (int i = 1; i <= room.getNuBed(); i++) {
            BedDTO bedDTO = new BedDTO(null, room.getCdRoom(), room.getDeCodigo() + "-"+ i, null);
            this.bedService.save(bedDTO);
        }
    }
}
