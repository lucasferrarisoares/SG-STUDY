package com.example.springboot.enumerated.status;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class StatusController {

    @GetMapping("/status")
    public ResponseEntity<List<StatusDTO>> listStatus() {
        List<StatusDTO> dtos = Arrays.stream(Status.values())
                .map(s -> new StatusDTO(s.getCdStatus(), s.getDeName()))
                .toList();
        return ResponseEntity.ok(dtos);
    }
}