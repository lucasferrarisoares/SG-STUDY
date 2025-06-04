package com.example.springboot.enumerated.status;

import lombok.Getter;

@Getter
public enum Status {
    FREE(0, "FREE"),
    BUSY(1, "BUSY"),
    CLEANING(2, "CLEANING"),
    MAINTENANCE(3, "MAINTENANCE");

    private final int cdStatus;
    private final String deName;

    Status(int cdStatus, String deName) {
        this.cdStatus = cdStatus;
        this.deName = deName;
    }

    // Buscar por código
    public static Status fromcdStatus(Integer cdStatus) {
        for (Status esp : Status.values()) {
            if (esp.getCdStatus() == cdStatus) {
                return esp;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + cdStatus);
    }

    // Buscar por deName (ignorando maiúsculas/minúsculas)
    public static Status fromdeName(String deName) {
        for (Status esp : Status.values()) {
            if (esp.getDeName().equalsIgnoreCase(deName)) {
                return esp;
            }
        }
        throw new IllegalArgumentException("deName inválido: " + deName);
    }
}
