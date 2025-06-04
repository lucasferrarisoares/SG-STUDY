package com.example.springboot.bed.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BedDTO(Integer cdStatus, @NotNull Long cdRoom, @NotBlank String deCodigo, Long cdPatient) {
}
