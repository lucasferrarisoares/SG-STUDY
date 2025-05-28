package com.example.springboot.pessoa.service;

import com.example.springboot.pessoa.DTO.PessoaDTO;
import com.example.springboot.pessoa.model.PessoaModel;
import com.example.springboot.pessoa.repositorio.PessoaRepositorio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepositorio pessoaRepositorio;

    public PessoaModel findById(UUID id) {
        return pessoaRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrado"));
    }
    public List<PessoaModel> listAll() {
        return pessoaRepositorio.findAll();
    }

    public PessoaModel save(@RequestBody @Valid PessoaDTO pessoaDTO) {
        PessoaModel pessoa = new PessoaModel();
        BeanUtils.copyProperties(pessoaDTO, pessoa);
        pessoaRepositorio.save(pessoa);
        return pessoa;
    }

    public PessoaModel update(@NotNull PessoaModel pessoa) {
        return pessoaRepositorio.save(pessoa);
    }

    public void delete(@NotNull PessoaModel pessoa) {
        pessoaRepositorio.delete(pessoa);
    }
}
