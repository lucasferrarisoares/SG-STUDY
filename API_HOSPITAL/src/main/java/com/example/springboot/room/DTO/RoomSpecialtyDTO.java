package com.example.springboot.room.DTO;

import com.example.springboot.enumerated.specialty.Specialty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomSpecialtyDTO(@NotNull Long cdRoom, @NotBlank String deCode, @NotNull Specialty specialty, @NotNull Long cdHWing) {
}
