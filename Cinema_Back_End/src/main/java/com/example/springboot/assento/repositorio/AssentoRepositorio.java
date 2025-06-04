package com.example.springboot.assento.repositorio;

import com.example.springboot.assento.model.AssentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AssentoRepositorio extends JpaRepository<AssentoModel, UUID> {
    @Query(nativeQuery = true,
            value = "SELECT A.* FROM CEH_ASSENTO A " +
                    "JOIN CEH_SESSAO S ON A.CD_SESSAO = S.CD_SESSAO " +
                    "WHERE S.CD_FILME = :idFilme AND A.CD_PESSOA NOTNULL")
    List<AssentoModel> searchSeatsMovie(@Param("idFilme") UUID idFilme);
}
