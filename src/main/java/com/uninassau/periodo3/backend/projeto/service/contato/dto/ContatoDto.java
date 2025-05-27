package com.uninassau.periodo3.backend.projeto.service.contato.dto;

import jakarta.validation.constraints.NotBlank;

public record ContatoDto(
        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String mensagem
) {
}
