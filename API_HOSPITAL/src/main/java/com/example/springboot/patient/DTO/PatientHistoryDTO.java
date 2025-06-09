package com.example.springboot.patient.DTO;

import com.example.springboot.enumerated.specialty.Specialty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PatientHistoryDTO(@NotBlank String ptName, @NotNull Specialty deSpecialty,
                                @NotNull Date dtHospitalization, Date dtDischarge) {
}
