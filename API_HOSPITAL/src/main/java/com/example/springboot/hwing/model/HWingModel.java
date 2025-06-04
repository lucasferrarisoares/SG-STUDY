package com.example.springboot.hwing.model;

import com.example.springboot.hospital.model.HospitalModel;
import com.example.springboot.enumerated.specialty.Specialty;
import com.example.springboot.enumerated.specialty.SpecialtyConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "CEH_HWing")
@Getter
@Setter
public class HWingModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_HWING", updatable = false, nullable = false)
    private Long cdHWing;

    @Column(name = "DE_SPECIALTY")
    @Convert(converter = SpecialtyConverter.class)
    private Specialty deSpecialty;

    @ManyToOne()
    @JoinColumn(name = "CD_HOSPITAL")
    private HospitalModel cdHospital;
}
