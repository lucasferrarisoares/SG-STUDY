package com.example.springboot.doctor.model;


import com.example.springboot.enumerated.specialty.Specialty;
import com.example.springboot.enumerated.specialty.SpecialtyConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CEH_DOCTOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_PATIENT")
    private Long cdDoctor;

    @Column(name = "DE_NAME", nullable = false)
    private String deName;

    @Column(name = "NU_CRM", nullable = false, unique = true, length = 20)
    private String nuCRM;

    @Convert(converter = SpecialtyConverter.class)
    @Column(name = "CD_SPECIALTY")
    private Specialty cdSpecialty;
}

