package com.example.springboot.patient.DTO;

import com.example.springboot.enumerated.specialty.Specialty;
import com.example.springboot.hwing.model.HWingModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

public record PatientHospitalizationDTO(@NotBlank String hpName, @NotNull Specialty specialty,
                                        @NotNull Long cdHwing, @NotNull Long cdRoom,
                                        @NotBlank String ptName, @NotNull Date dtHospitalization) {
}
