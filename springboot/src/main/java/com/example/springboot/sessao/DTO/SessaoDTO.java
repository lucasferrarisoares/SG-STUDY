package com.example.springboot.sessao.DTO;

import com.example.springboot.filme.model.FilmeModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;


public record SessaoDTO(@NotNull Integer nuSessao, @NotNull LocalTime hrInicio,
                        @NotNull LocalTime hrFim, @NotNull UUID cdFilme) {

    public UUID getCdFilme() {
        return cdFilme;
    }
}
