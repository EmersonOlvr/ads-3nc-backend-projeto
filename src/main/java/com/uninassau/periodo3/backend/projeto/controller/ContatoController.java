package com.uninassau.periodo3.backend.projeto.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uninassau.periodo3.backend.projeto.domain.Contato;
import com.uninassau.periodo3.backend.projeto.exception.ValidationHandler;
import com.uninassau.periodo3.backend.projeto.service.contato.CreateContatoUseCase;
import com.uninassau.periodo3.backend.projeto.service.contato.DeleteContatoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.contato.FindAllContatosUseCase;
import com.uninassau.periodo3.backend.projeto.service.contato.FindContatoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.contato.UpdateContatoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.contato.dto.ContatoDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contatos")
@Tag(name = "Contato", description = "Gerenciamento dos contatos")
public class ContatoController {
	
	@Autowired
	private CreateContatoUseCase createContatoUseCase;

	@Autowired
	private FindAllContatosUseCase findAllContatosUseCase;

	@Autowired
	private FindContatoByIdUseCase findContatoByIdUseCase;

	@Autowired
	private UpdateContatoByIdUseCase updateContatoByIdUseCase;

	@Autowired
	private DeleteContatoByIdUseCase deleteContatoByIdUseCase;

	@PostMapping
	@Operation(summary = "Cria um novo contato")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "201",
			description = "Contato criado com sucesso",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = Contato.class)
			)
		),
		@ApiResponse(
			responseCode = "400",
			description = "Dados inválidos",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = ValidationHandler.ValidationErrorResponse.class)
			)
		)
	})
	public ResponseEntity<Contato> create(@RequestBody @Valid @Parameter(description = "Dados do contato") ContatoDto contatoDto) {
		return ResponseEntity.status(HttpStatus.CREATED)
							.body(createContatoUseCase.execute(contatoDto));
	}

	@GetMapping
	@Operation(summary = "Lista todos os contatos")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Lista de contatos retornada com sucesso",
			content = @Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = Contato.class))
			)
		)
	})
	public ResponseEntity<List<Contato>> findAll() {
		List<Contato> contatos = findAllContatosUseCase.execute();
		return ResponseEntity.ok(contatos);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtém os detalhes de um contato a partir do seu ID")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Contato encontrado com sucesso",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = Contato.class)
			)
		),
		@ApiResponse(
			responseCode = "404",
			description = "Contato não encontrado",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = ValidationHandler.ErrorResponse.class)
			)
		)
	})
	public ResponseEntity<Contato> findById(@PathVariable @Parameter(description = "ID do contato") UUID id) {
		return ResponseEntity.ok(findContatoByIdUseCase.execute(id));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um contato a partir do seu ID")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Contato atualizado com sucesso",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = Contato.class)
			)
		),
		@ApiResponse(
			responseCode = "400",
			description = "Dados inválidos",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = ValidationHandler.ValidationErrorResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "404",
			description = "Contato não encontrado",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = ValidationHandler.ErrorResponse.class)
			)
		)
	})
	public ResponseEntity<Contato> update(@PathVariable @Parameter(description = "ID do contato") UUID id,
										@RequestBody @Valid @Parameter(description = "Novos dados do contato") ContatoDto contatoDto) 
	{
		return updateContatoByIdUseCase.execute(id, contatoDto)
									   .map(ResponseEntity::ok)
									   .orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um contato a partir do seu ID")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "204",
			description = "Contato excluído com sucesso"
		)
	})
	public ResponseEntity<Void> delete(
		@PathVariable 
		@Parameter(description = "ID do contato") UUID id) 
	{
		deleteContatoByIdUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}
	
}
