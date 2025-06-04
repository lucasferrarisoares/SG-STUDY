package com.example.springboot.hospitalizationslog.DTO;

import com.example.springboot.enumerated.specialty.Specialty;
import jakarta.validation.constraints.NotNull;

public record HospitalizationsFinalDTO(@NotNull Long pacient, @NotNull Long wing, @NotNull Long room, @NotNull Long bed) {
}
