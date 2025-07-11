package com.example.springboot.patient.DTO;

import java.util.List;

public record PageResponseDTO<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        boolean last
) {}
