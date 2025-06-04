package com.example.springboot.patient.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "CEH_PATIENT")
@Getter
@Setter
public class PatientModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_PATIENT", updatable = false, nullable = false)
    private Long cdPatient;

    @Column(name = "DE_PATIENT", length = 255)
    private String dePatient;
}
