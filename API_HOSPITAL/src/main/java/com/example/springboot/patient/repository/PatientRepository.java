package com.example.springboot.patient.repository;

import com.example.springboot.patient.model.PatientModel;
import com.example.springboot.patient.projection.PacientHospitalizationProjection;
import com.example.springboot.patient.projection.PatientHistoryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long> {

    @Query (nativeQuery = true,
    value = "SELECT EXISTS ( " +
        "SELECT 1 FROM CEH_LEITO WHERE CD_PATIENT = :cdPatient) ")
    boolean verifyHospitalizationByPatient(Long cdPatient);

    @Query(nativeQuery = true,
            value=
                    "select" +
                            "   HP.DT_DISCHARGE as dtDischarge, " +
                            "   HP.de_specialty as deSpecialty, " +
                            "   P.de_patient as ptName,  " +
                            "   HP.dt_hospitalization as dtHospitalization " +
                            "FROM" +
                            "   CEH_HOSPITALIZATIONSLOG HP " +
                            "JOIN CEH_PATIENT P ON P.CD_PATIENT = HP.CD_PATIENTE " +
                            "WHERE" +
                            "   HP.CD_PATIENTE = :cdPatient " +
                            "   ORDER BY HP.DT_HOSPITALIZATION DESC ")
    Page<PatientHistoryProjection> findHistoryHospitalization(@Param("cdPatient") Long cdPatient, Pageable pageable);

    @Query(nativeQuery = true,
            value=
                "select" +
                "   ch3.cd_hospitalizationslog as cdHospitalization, " +
                "   CH2.de_hospital as hpName, " +
                "   CH.cd_hwing as hWingModel, " +
                "   CH.de_specialty as specialty, " +
                "   CR.cd_room as cdRoom, " +
                "   CP.de_patient as ptName,  " +
                "   TO_CHAR(ch3.dt_hospitalization, 'DD/MM/YYYY - HH24:MI')  as dtHospitalization " +
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
