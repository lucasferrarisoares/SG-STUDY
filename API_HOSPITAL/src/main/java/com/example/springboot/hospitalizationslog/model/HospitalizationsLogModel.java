package com.example.springboot.hospitalizationslog.model;

import com.example.springboot.enumerated.specialty.Specialty;
import com.example.springboot.enumerated.specialty.SpecialtyConverter;
import com.example.springboot.hwing.model.HWingModel;
import com.example.springboot.patient.model.PatientModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CEH_HOSPITALIZATIONSLOG")
@Getter
@Setter
public class HospitalizationsLogModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_HOSPITALIZATIONSLOG", updatable = false, nullable = false)
    private Long cdHospitalizationsLog;

    @ManyToOne()
    @JoinColumn(name = "CD_HWING")
    private HWingModel cdHWing;

    @ManyToOne()
    @JoinColumn(name = "CD_PATIENTE")
    private PatientModel cdPatient;

    @Column(name = "DT_HOSPITALIZATION")
    private Date dtHospitalization;

    @Column(name = "DT_DISCHARGE")
    private Date dtDischarge;

    @Column(name = "DE_SPECIALTY")
    @Convert(converter = SpecialtyConverter.class)
    private Specialty deSpecialty;
}
