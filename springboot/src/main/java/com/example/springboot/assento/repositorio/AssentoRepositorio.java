package com.example.springboot.assento.repositorio;

import com.example.springboot.assento.model.AssentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssentoRepositorio extends JpaRepository<AssentoModel, UUID> {
    @Query(nativeQuery = true,
            value = "SELECT A.* FROM CEH_ASSENTO A " +
                    "JOIN CEH_SESSAO S ON A.CD_SESSAO = S.CD_SESSAO " +
                    "WHERE S.CD_FILME = :idFilme AND A.CD_PESSOA NOTNULL")
    List<AssentoModel> searchSeatsMovie(@Param("idFilme") UUID idFilme);

    @Query(nativeQuery = true,
            value = "SELECT A.* FROM CEH_ASSENTO A " +
                    "JOIN CEH_SESSAO S ON S.CD_SESSAO = :idSessao " +
                    "AND A.CD_PESSOA IS NULL")
    List<AssentoModel> listFreeSeats(@Param("idSessao")UUID idSessao);

    @Query(nativeQuery = true,
            value = "SELECT A.* FROM CEH_ASSENTO A " +
                    "WHERE A.NU_ASSENTO = :nuSeat " +
                    "AND A.DE_FILEIRA = :rowSeat " +
                    "AND A.CD_PESSOA IS NULL " +
                    "AND A.CD_SESSAO = :cdSessao ")
    Optional<AssentoModel> findFreeByRowandNu(@Param("nuSeat")Integer nuSeat, @Param("rowSeat")String rowSeat, @Param("cdSessao")UUID cdSessao);
}
