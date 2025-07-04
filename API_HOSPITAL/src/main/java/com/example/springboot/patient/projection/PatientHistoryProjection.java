package com.example.springboot.patient.projection;

import com.example.springboot.enumerated.specialty.Specialty;


import java.util.Date;

public interface PatientHistoryProjection {
    String getPtName();
    Specialty getDeSpecialty();
    Date getDtHospitalization();
    Date getDtDischarge();
}
