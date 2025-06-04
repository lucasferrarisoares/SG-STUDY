package com.example.springboot.room.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomDTO(@NotBlank String deCodigo, @NotNull Integer cdStatus, @NotNull Long cdHWing, @NotNull int nuBed) {
}
