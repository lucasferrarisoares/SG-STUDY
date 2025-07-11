package com.example.springboot.patient.projection;

import java.util.Date;

public interface PacientHospitalizationProjection {
    Long getCdHospitalization();
    String getHpName();
    Long getHWingModel();
    Integer getSpecialty();
    Long getCdRoom();
    String getPtName();
    String getDtHospitalization();
}
