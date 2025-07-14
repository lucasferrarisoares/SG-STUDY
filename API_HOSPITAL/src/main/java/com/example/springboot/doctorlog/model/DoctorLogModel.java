package com.example.springboot.doctorlog.model;

import com.example.springboot.doctor.model.DoctorModel;
import com.example.springboot.hospitalizationslog.model.HospitalizationsLogModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "DOCTOR_LOG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DoctorLogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdDoctorLog;

    @ManyToOne
    @JoinColumn(name = "admission_id", nullable = false)
    private HospitalizationsLogModel hospitalizationsLogModel;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorModel doctor;

    @Column(name = "DT_HOSPITALIZATION", nullable = false)
    private LocalDateTime dtHospitalization;

    @Column(name = "DT_RELEASE")
    private LocalDateTime dtRelease;
}
