package com.example.springboot.hospitalizationslog.projection;

import java.util.Date;

public interface HospitalizationProjection {
    String getDePatient();
    Integer getCdSpecialty();
    Date getDtHospitalization();
    Integer getNuHospitalization();
}
