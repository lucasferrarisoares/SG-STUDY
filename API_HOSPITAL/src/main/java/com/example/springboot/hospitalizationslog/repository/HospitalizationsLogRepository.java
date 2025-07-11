package com.example.springboot.hospitalizationslog.repository;

import com.example.springboot.hospitalizationslog.model.HospitalizationsLogModel;
import com.example.springboot.hospitalizationslog.projection.HospitalizationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalizationsLogRepository extends JpaRepository<HospitalizationsLogModel, Long> {

    @Query(nativeQuery = true,
            value = "SELECT H FROM ceh_hospitalizationslog H " +
                    "WHERE H.DT_DISCHARGE IS NULL LIMIT 1")
    HospitalizationsLogModel findHospitalizedByPatient(@Param("cdPatient") Long cdPatient);

    @Query(nativeQuery = true, value =
            "select       " +
                    "P.cd_patient as cdPatient,     " +
                    "P.de_patient as dePatient ,      " +
                    "HH.DE_SPECIALTY as cdSpecialty,      " +
                    "TO_CHAR(HH.DT_HOSPITALIZATION, 'DD/MM/YYYY - HH24:MI')as dtHospitalization,      " +
                    "EXTRACT(DAY FROM CURRENT_DATE - HH.DT_HOSPITALIZATION) AS nuHospitalization     " +
                    "from      " +
                    "ceh_hospitalizationslog HH      " +
                    "join       " +
                    "ceh_patient P on P.cd_patient = HH.cd_patiente      " +
                    "where      " +
                    "HH.dt_discharge is null       " +
                    "order by      " +
                    "hh.de_specialty, p.de_patient      ")
    List<HospitalizationProjection> listActiveHospitalizations();
}
