package com.example.springboot.sessao.controller;



import com.example.springboot.sessao.DTO.SessaoDTO;
import com.example.springboot.sessao.model.SessaoModel;
import com.example.springboot.sessao.service.SessaoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SessaoController {
    @Autowired
    SessaoService sessaoService;

    @PostMapping("/sessoes")
    public ResponseEntity<SessaoModel> saveSessao(@RequestBody @Valid SessaoDTO sessaoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoService.save(sessaoDTO));
    }

    @GetMapping("/sessoes")
    public ResponseEntity<List<SessaoModel>> getAllSessaos() {
        return ResponseEntity.status(HttpStatus.OK).body(sessaoService.listAll());
    }

    @GetMapping("/sessoes/{cdsessao}")
    public ResponseEntity<Object> getOneSessao(@PathVariable(value="cdsessao") UUID id) {
        SessaoModel sessao = sessaoService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(sessao);
    }

    @PutMapping("/sessoes/{cdsessao}")
    public ResponseEntity<Object> updateSessao(@PathVariable(value="cdsessao") UUID cdsessao,
                                               @RequestBody @Valid SessaoDTO sessaoDTO) {
        SessaoModel sessao = sessaoService.findById(cdsessao);
        BeanUtils.copyProperties(sessaoDTO, sessao);
        return ResponseEntity.status(HttpStatus.OK).body(sessaoService.update(sessao));
    }

    @DeleteMapping("/sessoes/{cdsessao}")
    public ResponseEntity<Object> deleteSessao(@PathVariable(value="cdsessao") UUID cdsessao) {
        SessaoModel sessao = sessaoService.findById(cdsessao);

        sessaoService.delete(sessao);
        return ResponseEntity.status(HttpStatus.OK).body("sessao deletada com sucesso");
    }
}
