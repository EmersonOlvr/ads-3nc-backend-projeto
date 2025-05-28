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

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;
import com.uninassau.periodo3.backend.projeto.exception.ValidationHandler;
import com.uninassau.periodo3.backend.projeto.service.agendamento.CreateAgendamendoUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.DeleteAgendamentoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.FindAgendamentoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.FindAllAgendamentosUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.UpdateAgendamentoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.dto.AgendamentoDto;

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
@RequestMapping("/agendamento")
@Tag(name = "Agendamento", description = "Gerenciamento dos agendamentos de visita")
public class AgendamentoController {

	@Autowired
	private CreateAgendamendoUseCase createAgendamendoUseCase;

	@Autowired
	private FindAgendamentoByIdUseCase findAgendamentoByIdUseCase;

	@Autowired
	private FindAllAgendamentosUseCase findAllAgendamentosUseCase;

	@Autowired
	private UpdateAgendamentoByIdUseCase updateAgendamentoByIdUseCase;

	@Autowired
	private DeleteAgendamentoByIdUseCase deleteAgendamentoByIdUseCase;

	@PostMapping
	@Operation(summary = "Cria um novo agendamento")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "201", 
			description = "Agendamento criado com sucesso",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = Agendamento.class)
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
			responseCode = "409", 
			description = "Agendamento já existente para o dia e horário informado",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = ValidationHandler.ErrorResponse.class)
			)
		)
	})
	public ResponseEntity<Agendamento> create(@RequestBody @Valid @Parameter(description = "Dados do agendamento") AgendamentoDto agendamentoDto) {
		return ResponseEntity.ok(createAgendamendoUseCase.execute(agendamentoDto));
	}

	@GetMapping
	@Operation(summary = "Lista todos os agendamentos existentes")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Lista de agendamentos retornada com sucesso",
			content = @Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = Agendamento.class))
			)
		)
	})
	public ResponseEntity<List<Agendamento>> findAll() {
		List<Agendamento> agendamentos = findAllAgendamentosUseCase.execute();
		return ResponseEntity.status(HttpStatus.OK)
							.body(agendamentos);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtém os detalhes de um agendamento a partir do seu ID")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Agendamento encontrado com sucesso",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = Agendamento.class)
			)
		),
		@ApiResponse(
			responseCode = "404",
			description = "Agendamento não encontrado",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = ValidationHandler.ErrorResponse.class)
			)
		)
	})
	public ResponseEntity<Agendamento> findById(@PathVariable @Parameter(description = "ID do agendamento") UUID id) {
		return ResponseEntity.ok(findAgendamentoByIdUseCase.execute(id));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um agendamento a partir do seu ID")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Agendamento atualizado com sucesso",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = Agendamento.class)
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
			description = "Agendamento não encontrado",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = ValidationHandler.ErrorResponse.class)
			)
		)
	})
	public ResponseEntity<Agendamento> update(@PathVariable @Parameter(description = "ID do agendamento") UUID id, 
											@RequestBody @Valid @Parameter(description = "Novos dados do agendamento") AgendamentoDto agendamentoDto) 
	{
		return updateAgendamentoByIdUseCase.execute(id, agendamentoDto)
											.map(ResponseEntity::ok)
											.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui/cancela um agendamento a partir do seu ID")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "204",
			description = "Agendamento excluído com sucesso"
		)
	})
	public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "ID do agendamento") UUID id) {
		deleteAgendamentoByIdUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}
	
}
