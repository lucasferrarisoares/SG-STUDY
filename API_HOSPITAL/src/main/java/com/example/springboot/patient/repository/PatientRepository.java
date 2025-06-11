package com.example.springboot.patient.repository;

import com.example.springboot.patient.model.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long> {

    @Query (nativeQuery = true,
    value = "SELECT EXISTS ( " +
        "SELECT 1 FROM CEH_LEITO WHERE CD_PATIENT = :cdPatient) ")
    boolean verifyFreeBed(Long cdPatient);
}
