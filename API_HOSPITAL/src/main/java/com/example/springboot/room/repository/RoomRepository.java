package com.example.springboot.room.repository;

import com.example.springboot.bed.model.BedModel;
import com.example.springboot.room.model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, Long> {

    @Query(nativeQuery = true,
            value = "SELECT R.* FROM CEH_ROOM R " +
                    "JOIN CEH_LEITO L ON L.CD_ROOM = R.CD_ROOM " +
                    "WHERE L.CD_PATIENT = :cdPatient  ")
    RoomModel findByPatient(@Param("cdPatient") Long cdPatient);
}
