package com.example.springboot.room.model;

import com.example.springboot.hwing.model.HWingModel;
import com.example.springboot.enumerated.status.Status;
import com.example.springboot.enumerated.status.StatusConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "CEH_ROOM")
@Getter
@Setter
public class RoomModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_ROOM", updatable = false, nullable = false)
    private Long cdRoom;

    @Column(name = "DE_CODE", length = 10)
    private String deCodigo;

    @Column(name = "CD_STATUS")
    @Convert(converter = StatusConverter.class)
    private Status cdStatus;

    @ManyToOne()
    @JoinColumn(name = "CD_HWING")
    private HWingModel cdHWing;
}
