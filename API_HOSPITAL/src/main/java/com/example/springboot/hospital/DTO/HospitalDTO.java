package com.example.springboot.hospital.DTO;

import jakarta.validation.constraints.NotBlank;

public record HospitalDTO(@NotBlank String deHospital) {
}
