package com.example.springboot.sessao.repositorio;

import com.example.springboot.sessao.model.SessaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SessaoRepositorio extends JpaRepository<SessaoModel, UUID> {

    @Query(nativeQuery = true,
            value = "SELECT S.* FROM CEH_SESSAO S " +
                    "JOIN CEH_FILME F ON F.CD_FILME = S.CD_FILME ")
    List<SessaoModel> searchSessionMovie(@Param("idFilme") UUID idFilme);
}
