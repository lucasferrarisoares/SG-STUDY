package com.example.springboot.hwing.DTO;

import jakarta.validation.constraints.NotNull;

public record HWingDTO(@NotNull Integer cdSpecialty, @NotNull long cdHospital, @NotNull Integer nuRoom, @NotNull Integer nuBed) {
}
