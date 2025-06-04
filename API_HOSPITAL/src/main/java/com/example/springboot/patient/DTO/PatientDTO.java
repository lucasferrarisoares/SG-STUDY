package com.example.springboot.patient.DTO;

import jakarta.validation.constraints.NotBlank;

public record PatientDTO(@NotBlank String dePatient) {
}
