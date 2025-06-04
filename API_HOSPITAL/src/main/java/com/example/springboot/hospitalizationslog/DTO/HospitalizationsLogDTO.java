package com.example.springboot.hospitalizationslog.DTO;

import com.example.springboot.enumerated.specialty.Specialty;
import jakarta.validation.constraints.NotNull;

public record HospitalizationsLogDTO(@NotNull Specialty specialty, @NotNull Long cdPatient, @NotNull Long cdHWing) {
}
