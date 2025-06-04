package com.example.springboot.filme.repositorio;

import com.example.springboot.filme.model.FilmeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface FilmeRepositorio extends JpaRepository<FilmeModel, UUID> {
    @Query(nativeQuery = true,
            value = "SELECT F.* FROM CEH_FILME F " +
                    "WHERE :data BETWEEN DT_INICIO_CARTAZ AND F.DT_FIM_CARTAZ ")
    List<FilmeModel> findFilmByDay(@Param("data") LocalDate data);

}
