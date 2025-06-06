package com.example.springboot.patient.repository;

import com.example.springboot.patient.DTO.PatientHospitalizationDTO;
import com.example.springboot.patient.model.PatientModel;
import com.example.springboot.patient.projection.PacientHospitalizationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long> {
    
    @Query(nativeQuery = true,
            value=
                "select" +
                "   CH2.de_hospital as hpName, " +
                "   CH.cd_hwing as hWingModel, " +
                "   CH.de_specialty as specialty, " +
                "   CR.cd_room as cdRoom, " +
                "   CP.de_patient as ptName,  " +
                "   ch3.dt_hospitalization as dtHospitalization " +
                "from" +
                "   ceh_patient cp " +
                "join ceh_leito cl on " +
                "   CL.cd_patient = CP.cd_patient " +
                "join ceh_room cr on " +
                "   CR.cd_room = CL.cd_room " +
                "join ceh_hwing ch on " +
                "   CH.cd_hwing = CR.cd_hwing " +
                "join ceh_hospital ch2 on " +
                "   CH.cd_hospital = CH2.cd_hospital " +
                "join ceh_hospitalizationslog ch3 on " +
                "   ch3.cd_patiente = cp.cd_patient " +
                " where CP.cd_patient  = :cdPatient " +
                "   and ch3.dt_discharge is null ")
    PacientHospitalizationProjection findPatientHospitalizationInfo(@Param("cdPatient") Long cdPatient);
}
