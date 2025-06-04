package com.example.springboot.pessoa.controller;


import com.example.springboot.pessoa.service.PessoaService;
import com.example.springboot.pessoa.DTO.PessoaDTO;
import com.example.springboot.pessoa.model.PessoaModel;
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
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/pessoas")
    public ResponseEntity<PessoaModel> savePessoa(@RequestBody @Valid PessoaDTO pessoaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaDTO));
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<PessoaModel>> getAllPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listAll());
    }

    @GetMapping("/pessoas/{cdPessoa}")
    public ResponseEntity<Object> getOnePessoa(@PathVariable(value="cdPessoa") UUID id) {
        PessoaModel pessoa = pessoaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @PutMapping("/pessoas/{cdPessoa}")
    public ResponseEntity<Object> updatePessoa(@PathVariable(value="cdPessoa") UUID cdPessoa,
                                               @RequestBody @Valid PessoaDTO pessoaDTO) {
        PessoaModel pessoa = pessoaService.findById(cdPessoa);
        BeanUtils.copyProperties(pessoaDTO, pessoa);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.update(pessoa));
    }

    @DeleteMapping("/pessoas/{cdPessoa}")
    public ResponseEntity<Object> deletePessoa(@PathVariable(value="cdPessoa") UUID cdPessoa) {
        PessoaModel pessoa = pessoaService.findById(cdPessoa);
        pessoaService.delete(pessoa);
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada com sucesso");
    }
}
