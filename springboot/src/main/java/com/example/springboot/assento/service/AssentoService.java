package com.example.springboot.assento.service;


import com.example.springboot.assento.DTO.AssentoDTO;
import com.example.springboot.assento.model.AssentoModel;
import com.example.springboot.assento.repositorio.AssentoRepositorio;
import com.example.springboot.pessoa.service.PessoaService;
import com.example.springboot.sessao.repositorio.SessaoRepositorio;
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
    private SessaoRepositorio sessaoRepository;

    public AssentoService(AssentoRepositorio assentoRepository, PessoaService pessoaService, SessaoRepositorio sessaoRepository) {
        this.assentoRepository = assentoRepository;
        this.pessoaService = pessoaService;
        this.sessaoRepository = sessaoRepository;
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
        assento.setCdSessao(sessaoRepository.findById(assentoDTO.cdSessao()).orElseThrow(() -> new RuntimeException("Sessão não encontrada")));
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

    public void generateSeatsBySession(UUID cdSessao) {
        String[] fileiras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (String fileira: fileiras) {
            for (int i = 1; i < 25; i++ ) {
                AssentoDTO assento = new AssentoDTO(i, fileira, null, cdSessao);
                save(assento);
            }
        }
    }

    public List<AssentoModel> listFreeSeat(UUID cdSessao) {
        return assentoRepository.listFreeSeats(cdSessao);
    }

    public Boolean verifySeatsFree(Integer nuSeat, String rowSeat, UUID cdSessao) {
        return assentoRepository.findFreeByRowandNu(nuSeat, rowSeat, cdSessao).isPresent();
    }

    public Optional<AssentoModel> findByRowandNu(Integer nuSeat, String rowSeat, UUID cdSessao) {
        return assentoRepository.findFreeByRowandNu(nuSeat, rowSeat, cdSessao);
    }
}
