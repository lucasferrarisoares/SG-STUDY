package com.example.springboot.hospitalizationslog.DTO;

import com.example.springboot.enumerated.specialty.Specialty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;

import java.util.Date;

public record HospitalizationActiveDTO(@NotNull Long cdPatient, @NotBlank String dePatient, @NotNull Specialty specialty,
                                       @NotBlank String dtHospitalization, @NotNull Integer nuHospitalization) {
}
