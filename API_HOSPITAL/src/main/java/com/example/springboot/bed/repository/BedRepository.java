package com.example.springboot.bed.repository;

import com.example.springboot.bed.model.BedModel;
import com.example.springboot.bed.projection.BedProjection;
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
                    "B.CD_PATIENT = :cdPatient ")
    BedModel findByPatient(@Param("cdPatient") Long cdPatient);

    @Query(nativeQuery = true,
            value = "SELECT L.* FROM CEH_LEITO L " +
                    "JOIN CEH_ROOM R ON L.CD_ROOM = R.CD_ROOM " +
                    "JOIN CEH_HWING W ON R.CD_HWING = W.CD_HWING " +
                    "WHERE L.DE_STATUS = 0  AND W.DE_SPECIALTY = :cdSpecialty       " +
                    "ORDER BY L.CD_BED")
    List<BedModel> findBySpecialty(@Param("cdSpecialty") Integer cdSpecialty);

    @Query(nativeQuery = true,
            value = "select     " +
                        "HL.CD_BED as cdBed,     " +
                        "P.de_patient as dePatient,     " +
                        "TO_CHAR(HL.dt_hospitalization, 'DD/MM/YYYY - HH24:MI') AS dtHospitalization, " +
                        "TO_CHAR(HL.dt_discharge, 'DD/MM/YYYY - HH24:MI') AS dtDischarge " +
                    "from ceh_hospitalizationslog HL      " +
                    "join ceh_patient P on      " +
                        "P.cd_patient = HL.cd_patiente     " +
                    "where     " +
                        "HL.cd_BED = :cdBed     " +
                    "order      " +
                        "by HL.dt_hospitalization desc")
    List<BedProjection> findHospitalizationLogByBed(@Param("cdBed") Long cdBed);
}
