package com.example.springboot.enumerated.specialty;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Specialty {
    CARDIOLOGY(0, "CARDIOLOGY"),
    NEUROLOGY(1, "NEUROLOGY"),
    ORTOPEDIA(2, "ORTHOPEDICS"),
    PEDIATRIA(3, "PEDIATRICS");

    private final Integer cdSpecialty;
    private final String deName;

    Specialty(Integer cdSpecialty, String deName) {
        this.cdSpecialty = cdSpecialty;
        this.deName = deName;
    }

    @JsonCreator
    public static Specialty fromcdSpecialty(Integer cdSpecialty) {
        for (Specialty esp : Specialty.values()) {
            if (esp.getCdSpecialty() == cdSpecialty) {
                return esp;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + cdSpecialty);
    }

    // Buscar por deName (ignorando maiúsculas/minúsculas)
    public static Specialty fromdeName(String deName) {
        for (Specialty esp : Specialty.values()) {
            if (esp.getDeName().equalsIgnoreCase(deName)) {
                return esp;
            }
        }
        throw new IllegalArgumentException("deName inválido: " + deName);
    }
}
