package com.uninassau.periodo3.backend.projeto.service.agendamento.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AgendamentoDto(
		@NotBlank String nome,
		@NotBlank String email,
		@NotBlank String telefone,
		@NotNull LocalDateTime data
) {
}
