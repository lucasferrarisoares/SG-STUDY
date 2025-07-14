package com.example.springboot.doctor.repository;

import com.example.springboot.doctor.model.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorModel, Long> {

    @Query(nativeQuery = true,
            value=
            "SELECT d FROM DoctorModel d " +
                "WHERE " +
                    "d.crm = :crm")
    Optional<DoctorModel> findDoctorByCRM(@Param("crm") Long crm);
}
