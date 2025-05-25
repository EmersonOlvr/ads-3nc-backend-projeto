package com.uninassau.periodo3.backend.atv_01.service.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaDto(
		@NotBlank String nome, 
		@NotBlank String cpf, 
		@NotNull LocalDate dataNascimento
) {

}
