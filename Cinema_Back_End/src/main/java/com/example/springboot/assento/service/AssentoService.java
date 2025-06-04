package com.example.springboot.assento.service;


import com.example.springboot.assento.DTO.AssentoDTO;
import com.example.springboot.assento.model.AssentoModel;
import com.example.springboot.assento.repositorio.AssentoRepositorio;
import com.example.springboot.pessoa.repositorio.PessoaRepositorio;
import com.example.springboot.pessoa.service.PessoaService;
import com.example.springboot.sessao.service.SessaoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssentoService {
    @Autowired
    private AssentoRepositorio assentoRepository;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private SessaoService sessaoService;

    public AssentoService(AssentoRepositorio assentoRepository, PessoaRepositorio pessoaRepository) {
        this.assentoRepository = assentoRepository;
        this.pessoaService = pessoaService;
        this.sessaoService = sessaoService;
    }

    public AssentoModel findById(UUID id) {
        return assentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Assento não encontrado"));
    }
    public List<AssentoModel> listAll() {
        return assentoRepository.findAll();
    }

    public AssentoModel save(@RequestBody @Valid AssentoDTO assentoDTO) {
        AssentoModel assento = new AssentoModel();

        BeanUtils.copyProperties(assentoDTO, assento);
        assento.setCdDono(pessoaService.findById(assentoDTO.getCdDono()));
        assento.setCdSessao(sessaoService.findById(assentoDTO.getCdSessao()));
        assentoRepository.save(assento);
        return assento;
    }

    public AssentoModel update(@NotNull AssentoModel assento) {
        return assentoRepository.save(assento);
    }

    public void delete(@NotNull AssentoModel assento) {
        assentoRepository.delete(assento);
    }

    public List<AssentoModel> searchSeatsMovie(@NotNull UUID cdFilme) {
        return assentoRepository.searchSeatsMovie(cdFilme);
    }
}
