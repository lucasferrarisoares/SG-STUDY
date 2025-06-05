package com.example.springboot.room.DTO;

import com.example.springboot.enumerated.specialty.Specialty;
import jakarta.validation.constraints.NotNull;

public record RoomNUDTO(@NotNull Specialty specialty, @NotNull Long freeRoom, @NotNull Long budyRoom, @NotNull Long totalRoom) {
}
