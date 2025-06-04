package com.example.springboot.bed.repository;

import com.example.springboot.bed.model.BedModel;
import com.example.springboot.hwing.model.HWingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedRepository extends JpaRepository<BedModel, Long> {
    @Query(nativeQuery = true,
            value = "SELECT L.* FROM CEH_LEITO L " +
                    "JOIN CEH_ROOM R ON L.CD_ROOM = R.CD_ROOM " +
                    "JOIN CEH_HWING W ON R.CD_HWING = W.CD_HWING " +
                    "WHERE L.DE_STATUS = 0  AND W.DE_SPECIALTY = :cdSpecialty LIMIT 1")
    BedModel findFreeBedBySpecialty(@Param("cdSpecialty") Integer cdSpecialty);

    @Query(nativeQuery = true,
            value = "SELECT B.* FROM CEH_LEITO B WHERE " +
                    "B.CD_PATIENT = :cdPatient AND ")
    BedModel findByiInpatient(@Param("cdPatient") Long cdPatient);
}
