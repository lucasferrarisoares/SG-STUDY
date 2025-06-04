package com.example.springboot.assento.model;

import com.example.springboot.pessoa.model.PessoaModel;
import com.example.springboot.sessao.model.SessaoModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Table(name = "CEH_ASSENTO")
@Entity
public class AssentoModel implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "CD_ASSENTO", updatable = false, nullable = false)
    private UUID cdAssento;

    @Column(name = "NU_ASSENTO")
    private int nuAssento;

    @Column(name = "DE_FILEIRA", length = 1)
    private String deFileira;

    @ManyToOne()
    @JoinColumn(name = "CD_SESSAO")
    private SessaoModel cdSessao;

    @ManyToOne()
    @JoinColumn(name = "CD_PESSOA")
    private PessoaModel cdDono;
}