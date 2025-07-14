package com.example.springboot.doctorlog.repository;

import com.example.springboot.doctorlog.model.DoctorLogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorLogRepository extends JpaRepository<DoctorLogModel, Long> {

    @Query(nativeQuery = true,
            value=
            "SELECT H FROM DoctorHistoryModel H " +
                    "WHERE " +
                        "H.admission.id = :admissionId " +
                    "AND " +
                        "H.endTime IS NULL")
    DoctorLogModel findActiveByAdmission(@Param("admissionId") Long admissionId);
}
