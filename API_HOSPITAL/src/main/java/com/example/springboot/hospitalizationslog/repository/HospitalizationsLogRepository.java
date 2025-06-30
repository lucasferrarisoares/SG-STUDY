package com.example.springboot.hospitalizationslog.repository;

import com.example.springboot.hospitalizationslog.model.HospitalizationsLogModel;
import com.example.springboot.hospitalizationslog.projection.HospitalizationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalizationsLogRepository extends JpaRepository<HospitalizationsLogModel, Long> {

    @Query(nativeQuery = true,
            value = "SELECT H FROM ceh_hospitalizationslog H " +
                    "WHERE H.DT_DISCHARGE IS NULL LIMIT 1")
    HospitalizationsLogModel findHospitalizedByPatient(@Param("cdPatient") Long cdPatient);

    @Query(nativeQuery = true, value =
            "select       " +
                    "P.de_patient as dePatient ,      " +
                    "W.DE_SPECIALTY as cdSpecialty,      " +
                    "HH.DT_HOSPITALIZATION as dtHospitalization,      " +
                    "(CURRENT_DATE - HH.DT_HOSPITALIZATION)      " +
                    "from      " +
                    "ceh_hospitalizationslog HH      " +
                    "join       " +
                    "ceh_patient P on P.cd_patient = HH.cd_patiente      " +
                    "join      " +
                    "ceh_hwing W on W.cd_hwing = HH.cd_hwing       " +
                    "where      " +
                    "HH.dt_discharge is null       " +
                    "order by      " +
                    "w.de_specialty, p.de_patient      ")
    List<HospitalizationProjection> listActiveHospitalizations();
}
