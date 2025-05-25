package com.uninassau.periodo3.backend.atv_01.agendamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AgendamentoDto(
        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String telefone,
        @NotNull LocalDate data
) {
}
