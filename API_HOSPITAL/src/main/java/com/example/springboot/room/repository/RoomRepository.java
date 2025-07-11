package com.example.springboot.room.repository;

import com.example.springboot.room.model.RoomModel;
import com.example.springboot.room.projection.RoomProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, Long> {

    @Query(nativeQuery = true,
            value ="select  " +
                    "R.cd_room as cdRoom,   " +
                    "R.de_code as deCode,    " +
                    "H.de_specialty as cdSpecialty     " +
                    "from ceh_room R    " +
                    "join ceh_hwing H on R.cd_hwing = H.cd_hwing        " +
                    "where R.cd_status = 0")
    List<RoomProjection> listFreeRoom();

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
    @Query(nativeQuery = true,
            value = "SELECT EXISTS (     " +
                    "    SELECT 1      " +
                    "       FROM CEH_LEITO L     " +
                    "    JOIN CEH_ROOM R      " +
                    "       ON L.CD_ROOM = R.CD_ROOM     " +
                    "    WHERE      " +
                    "       L.DE_STATUS = 0 " +
                    "   AND" +
                    "       R.CD_ROOM = :cdRoom   " +
                    ")"
    )
    Boolean verifyRoomIsFree(@Param("cdRoom") Long cdRoom);
}
