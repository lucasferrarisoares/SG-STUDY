package com.example.springboot.filme.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "CEH_Filme")
@Getter
@Setter
public class FilmeModel implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "CD_FILME", updatable = false, nullable = false)
    private UUID cdFilme;

    @Column(name = "DE_FILME", length = 255)
    private String deFilme;

    @Column(name = "DT_INICIO_CARTAZ")
    private Date dtInicioCartaz;

    @Column(name = "DT_Fim_CARTAZ")
    private Date dtFimCartaz;
}
