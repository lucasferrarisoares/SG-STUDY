package com.example.springboot.room.repository;

import com.example.springboot.room.model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, Long> {
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
