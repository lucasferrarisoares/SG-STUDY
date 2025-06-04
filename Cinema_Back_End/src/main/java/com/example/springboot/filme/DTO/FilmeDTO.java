package com.example.springboot.filme.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public record FilmeDTO(@NotBlank String deFilme, @NotNull Date dtInicioCartaz, @NotNull Date dtFimCartaz) {
}
