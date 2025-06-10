package com.example.springboot.hospitalizationslog.DTO;

import com.example.springboot.enumerated.specialty.Specialty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;

import java.util.Date;

public record HospitalizationActiveDTO(@NotBlank String dePatient, @NotNull Specialty specialty,
                                       @NotNull Date dtHospitalization, @NotNull Integer nuHospitalization) {
}
