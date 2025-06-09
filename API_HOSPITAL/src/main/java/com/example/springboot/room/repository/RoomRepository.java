package com.example.springboot.room.repository;

import com.example.springboot.room.DTO.RoomSpecialtyDTO;
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
                    "R.de_code as deCode,    " +
                    "H.de_specialty as cdSpecialty     " +
                    "from ceh_room R    " +
                    "join ceh_hwing H on R.cd_hwing = H.cd_hwing        " +
                    "where R.cd_status = 0")
    List<RoomProjection> listFreeRoom();
}
