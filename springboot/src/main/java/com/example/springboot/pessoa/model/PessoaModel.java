package com.example.springboot.pessoa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "CEH_Pessoa")
@Getter
@Setter
public class PessoaModel implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "CD_PESSOA", updatable = false, nullable = false)
    private UUID cdPessoa;

    @Column(name = "DE_PESSOA", length = 255)
    private String dePessoa;
}
