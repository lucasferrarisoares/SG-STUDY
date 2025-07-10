package com.example.springboot.patient.DTO;

import com.example.springboot.enumerated.specialty.Specialty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientHospitalizationDTO(@NotNull Long cdHospitalization, @NotBlank String hpName,
                                        @NotNull Specialty specialty, @NotNull Long cdHwing,
                                        @NotNull Long cdRoom, @NotBlank String ptName,
                                        @NotBlank String dtHospitalization) {
}
