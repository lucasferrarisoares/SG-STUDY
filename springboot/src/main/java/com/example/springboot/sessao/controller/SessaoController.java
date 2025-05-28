package com.example.springboot.sessao.controller;



import com.example.springboot.assento.DTO.AssentoDTO;
import com.example.springboot.assento.model.AssentoModel;
import com.example.springboot.assento.service.AssentoService;
import com.example.springboot.pessoa.service.PessoaService;
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
    @Autowired
    AssentoService assentoService;
    @Autowired
    PessoaService pessoaService;

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

    @GetMapping("/sessoes/{cdsessao}/assentos-livres")
    public ResponseEntity<Object> listFreeSeats(@PathVariable(value="cdsessao") UUID id) {
        List<AssentoModel> seats = assentoService.listFreeSeat(id);
        if (seats.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem cadeiras disponíveis");
        }
        return ResponseEntity.status(HttpStatus.OK).body(seats);
    }

    @PutMapping("/sessoes/{cdsessao}/comprar-ingresso")
    public ResponseEntity<Object> buyTicekt(@PathVariable(value="cdsessao") UUID id, @RequestBody @Valid AssentoDTO assentoDTO) {
            if (assentoService.verifySeatsFree(assentoDTO.nuAssento(),assentoDTO.deFileira(), id)) {
                AssentoModel assento = new AssentoModel();
                BeanUtils.copyProperties(assentoDTO, assento);
                assento.setCdAssento(assentoService.findByRowandNu(assentoDTO.nuAssento(), assentoDTO.deFileira(), id).orElseThrow().getCdAssento());
                assento.setCdDono(pessoaService.findById(assentoDTO.cdDono()));
                assento.setCdSessao(sessaoService.findById(id));
                return ResponseEntity.status(HttpStatus.OK).body(assentoService.update(assento));
            }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O assento desejado não está disponíveis");
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
