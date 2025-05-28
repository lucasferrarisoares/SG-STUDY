package com.example.springboot.assento.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;



public record AssentoDTO(@NotNull Integer nuAssento, @NotBlank String deFileira,
                         UUID cdDono, UUID cdSessao) {
}
