package com.example.springboot.bed.projection;

import java.util.Date;

public interface BedProjection {
    Long getCdBed();
    String getDePatient();
    Date getDtHospitalization();
    Date getDtDischarge();
}
