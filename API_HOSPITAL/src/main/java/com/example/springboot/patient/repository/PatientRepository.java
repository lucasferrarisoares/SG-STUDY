package com.example.springboot.patient.repository;

import com.example.springboot.patient.projection.PatientHistoryProjection;
import com.example.springboot.patient.model.PatientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long> {

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
                            "   HP.CD_PATIENTE = :cdPatient")
    Page<PatientHistoryProjection> findHistoryHospitalization(@Param("cdPatient") Long cdPatient, Pageable pageable);
}
