package com.example.springboot.filme.controller;


import com.example.springboot.assento.service.AssentoService;
import com.example.springboot.filme.DTO.FilmeDTO;
import com.example.springboot.filme.model.FilmeModel;
import com.example.springboot.filme.service.FilmeService;
import com.example.springboot.sessao.service.SessaoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class FilmeController {
    @Autowired
    FilmeService filmeService;

    @Autowired
    AssentoService assentoService;

    @Autowired
    SessaoService sessaoService;

    @PostMapping("/filmes")
    public ResponseEntity<Object> saveFilme(@RequestBody @Valid FilmeDTO filmeDTO) {
        FilmeModel filme = new FilmeModel();
        BeanUtils.copyProperties(filmeDTO, filme);
        if (!(validateDate(filme.getDtInicioCartaz(),filme.getDtFimCartaz()))) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("A data final está marcada para antes da Data Inicial");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeService.save(filme));
    }

    @GetMapping("/filmes")
    public ResponseEntity<List<FilmeModel>> getAllFilmes() {
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.listAll());
    }

    @GetMapping("/filmes/{cdfilme}")
    public ResponseEntity<Object> getOneFilme(@PathVariable(value="cdfilme") UUID id) {
        FilmeModel filme = filmeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(filme);
    }

    @PutMapping("/filmes/{cdfilme}")
    public ResponseEntity<Object> updateFilme(@PathVariable(value="cdfilme") UUID cdfilme,
                                               @RequestBody @Valid FilmeDTO filmeDTO) {
        FilmeModel filme = filmeService.findById(cdfilme);
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.update(filme));
    }

    @DeleteMapping("/filmes/{cdfilme}")
    public ResponseEntity<Object> deleteFilme(@PathVariable(value="cdfilme") UUID cdfilme) {
        FilmeModel filme = filmeService.findById(cdfilme);
        if (haveValidTickets(filme.getCdFilme())) {
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Filme selecionado tem sessões com assentos vendidos!");
        }
        sessaoService.deleteSessionByFilm(filme.getCdFilme());
        filmeService.delete(filme);
        return ResponseEntity.status(HttpStatus.OK).body("filme deletada com sucesso");
    }

    @GetMapping("/filmes/today")
    public ResponseEntity<Object> showAvaliableMoviesToday() {
        LocalDate dtHoje = LocalDate.now();
        List<FilmeModel> filmes = filmeService.listFilmsToday(dtHoje);
        if (filmes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Nenhum filme está em cartaz hoje");
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmes);

    }

    public Boolean validateDate(Date dtInicio, Date dtFinal) {
        return dtInicio.before(dtFinal);
    }

    public Boolean haveValidTickets(UUID cdFilme) {
        return !assentoService.searchSeatsMovie(cdFilme).isEmpty();
    }
}
