package com.uninassau.periodo3.backend.projeto.service.pets.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record PetDto(
		@NotBlank String nome,
		@NotBlank String especie,
		@NotBlank String raca,
		@NotNull LocalDate dataNascimento,
		@NotBlank String descricao,
		@NotBlank String foto_url
		) {
}
