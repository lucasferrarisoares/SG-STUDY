package com.example.springboot.pessoa.DTO;

import jakarta.validation.constraints.NotBlank;

public record PessoaDTO(@NotBlank String dePessoa) {
}
