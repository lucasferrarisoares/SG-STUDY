package com.example.springboot.pessoa.repositorio;

import com.example.springboot.pessoa.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaRepositorio extends JpaRepository<PessoaModel, UUID> {

    @Query(nativeQuery = true,
        value = "SELECT P.* FROM CEH_PESSOA P WHERE CD_PESSOA = :id")
    PessoaModel buscarPorID(@Param("id") UUID id);

}
