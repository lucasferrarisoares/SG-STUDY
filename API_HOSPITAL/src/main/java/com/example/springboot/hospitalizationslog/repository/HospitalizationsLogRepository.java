package com.example.springboot.hospitalizationslog.repository;

import com.example.springboot.hospitalizationslog.model.HospitalizationsLogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalizationsLogRepository extends JpaRepository<HospitalizationsLogModel, Long> {

    @Query(nativeQuery = true,
        value = "SELECT H.* FROM CEH_HOSPITAL H WHERE " +
                "H.CD_PATIENT = :cdPatient AND " +
                "H.DT_DISCHARGE IS NULL")
    HospitalizationsLogModel findHospitalizedByPatient(@Param("cdPatient") Long cdPatient);

}
