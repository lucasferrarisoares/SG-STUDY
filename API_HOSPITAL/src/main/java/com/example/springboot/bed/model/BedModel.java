package com.example.springboot.bed.model;

import com.example.springboot.enumerated.status.Status;
import com.example.springboot.enumerated.status.StatusConverter;
import com.example.springboot.patient.model.PatientModel;
import com.example.springboot.room.model.RoomModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "CEH_LEITO")
@Getter
@Setter
public class BedModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_BED", updatable = false, nullable = false)
    private Long cdBed;

    @Column(name = "DE_STATUS")
    @Convert(converter = StatusConverter.class)
    private Status cdStatus;

    @Column(name = "DE_CODE", length = 10)
    private String deCode;

    @ManyToOne()
    @JoinColumn(name = "CD_ROOM")
    private RoomModel cdRoom;

    @ManyToOne()
    @JoinColumn(name = "CD_PATIENT")
    private PatientModel cdPatient;
}
