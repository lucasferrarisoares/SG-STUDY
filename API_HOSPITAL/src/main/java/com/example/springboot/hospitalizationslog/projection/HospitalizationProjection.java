package com.example.springboot.hospitalizationslog.projection;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public interface HospitalizationProjection {
    Long getCdPatient();
    String getDePatient();
    Integer getCdSpecialty();
    String getDtHospitalization();
    Integer getNuHospitalization();
}
