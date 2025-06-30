package com.example.springboot.room.repository;

import com.example.springboot.room.model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, Long> {

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) FROM CEH_ROOM R JOIN CEH_HWING W ON R.CD_HWING = W.CD_HWING " +
                    "WHERE W.DE_SPECIALTY = :cdSpecialty AND R.CD_STATUS = 0")
    Long nuFreeRoomBySpecialty(@Param("cdSpecialty") Integer cdSpecialty);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) FROM CEH_ROOM R JOIN CEH_HWING W ON R.CD_HWING = W.CD_HWING " +
                    "WHERE W.DE_SPECIALTY = :cdSpecialty AND R.CD_STATUS != 0 ")
    Long nuBusyRoomBySpecialty(@Param("cdSpecialty") Integer cdSpecialty);

    @Query(nativeQuery = true,
            value = "SELECT R.* FROM CEH_ROOM R " +
                    "JOIN CEH_LEITO L ON L.CD_ROOM = R.CD_ROOM " +
                    "WHERE L.CD_PATIENT = :cdPatient  ")
    RoomModel findByPatient(@Param("cdPatient") Long cdPatient);
}
