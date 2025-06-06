package com.example.springboot.sessao.service;

import com.example.springboot.filme.service.FilmeService;
import com.example.springboot.sessao.DTO.SessaoDTO;
import com.example.springboot.sessao.model.SessaoModel;
import com.example.springboot.sessao.repositorio.SessaoRepositorio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepositorio sessaoRepository;
    private FilmeService filmeService;

    public SessaoService(SessaoRepositorio sessaoRepository, FilmeService filmeService) {
        this.sessaoRepository = sessaoRepository;
        this.filmeService = filmeService;
    }

    public SessaoModel findById(UUID id) {
        return sessaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Sessao não encontrada"));
    }
    public List<SessaoModel> listAll() {
        return sessaoRepository.findAll();
    }

    public void deleteSessionByFilm(UUID cdFilme) {
        List<SessaoModel> listaSessoes = sessaoRepository.searchSessionMovie(cdFilme);
        for (SessaoModel session: listaSessoes) {
            this.delete(session);
        }
    }

    public SessaoModel save(@RequestBody @Valid SessaoDTO sessaoDTO) {
        SessaoModel sessao = new SessaoModel();

        BeanUtils.copyProperties(sessaoDTO, sessao);
        sessao.setCdFilme(this.filmeService.findById(sessaoDTO.getCdFilme()));
        sessao.setFlAtivo(true);
        sessaoRepository.save(sessao);
        return sessao;
    }

    public SessaoModel update(@NotNull SessaoModel sessao) {
        return sessaoRepository.save(sessao);
    }

    public void delete(@NotNull SessaoModel sessao) {
        sessaoRepository.delete(sessao);
    }
}
