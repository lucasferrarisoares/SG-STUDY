package com.example.springboot.enumerated.specialty;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class SpecialtyController {

    @GetMapping("/specialties")
    public ResponseEntity<List<SpecialtyDTO>> listSpecialties() {
        List<SpecialtyDTO> dtos = Arrays.stream(Specialty.values())
                .map(s -> new SpecialtyDTO(s.getCdSpecialty(), s.getDeName()))
                .toList();
        return ResponseEntity.ok(dtos);
    }
}