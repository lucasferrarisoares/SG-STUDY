package com.example.springboot.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "CEH_Hospital")
@Getter
@Setter
public class HospitalModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_HOSPITAL", updatable = false, nullable = false)
    private Long cdHospital;

    @Column(name = "DE_HOSPITAL", length = 255)
    private String deHospital;
}
