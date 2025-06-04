package com.example.springboot.hospital.repository;

import com.example.springboot.hospital.model.HospitalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalModel, Long> {

    @Query(nativeQuery = true,
        value = "SELECT H.* FROM CEH_HOSPITAL H WHERE CD_HOSPITAL = :id")
    HospitalModel buscarPorID(@Param("id") Long id);

}
