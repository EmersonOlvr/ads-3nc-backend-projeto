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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uninassau.periodo3.backend.projeto.domain.Pet;
import com.uninassau.periodo3.backend.projeto.exception.ValidationHandler;
import com.uninassau.periodo3.backend.projeto.service.pets.CreatePetUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.DeletePetByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.FindAllPetsUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.FindPetByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.ListPetsByNomeAndRacaUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.UpdatePetByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.dto.PetDto;

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
@RequestMapping("/pets")
@Tag(name = "Pets", description = "Gerenciamento dos pets cadastrados")
public class PetsController {
	
	@Autowired
	private CreatePetUseCase createPetUseCase;

	@Autowired
	private FindAllPetsUseCase findAllPetsUseCase;

	@Autowired
	private FindPetByIdUseCase findPetByIdUseCase;

	@Autowired
	private ListPetsByNomeAndRacaUseCase listPetsByNomeAndRacaUseCase;

	@Autowired
	private UpdatePetByIdUseCase updatePetByIdUseCase;

	@Autowired
	private DeletePetByIdUseCase deletePetByIdUseCase;

	@PostMapping
	@Operation(summary = "Cria um novo pet")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "201",
			description = "Pet criado com sucesso",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = Pet.class)
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
	public ResponseEntity<Pet> create(@RequestBody @Valid @Parameter(description = "Dados do pet") PetDto petDto) {
		return ResponseEntity.status(HttpStatus.CREATED)
							.body(createPetUseCase.execute(petDto));
	}

	@GetMapping
	@Operation(summary = "Lista todos os pets cadastrados")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Lista de pets retornada com sucesso",
			content = @Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = Pet.class))
			)
		)
	})
	public ResponseEntity<List<Pet>> findAll() {
		List<Pet> pets = findAllPetsUseCase.execute();
		return ResponseEntity.ok(pets);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Busca um pet a partir do seu ID")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Pet encontrado com sucesso",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = Pet.class)
			)
		),
		@ApiResponse(
			responseCode = "404",
			description = "Pet não encontrado",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = ValidationHandler.ErrorResponse.class)
			)
		)
	})
	public ResponseEntity<Pet> findById(@PathVariable @Parameter(description = "ID do pet") UUID id) {
		return ResponseEntity.ok(findPetByIdUseCase.execute(id));
	}

	@GetMapping("/buscaPorNomeERaca")
	@Operation(summary = "Busca pets por nome e raça")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Lista de pets retornada com sucesso",
			content = @Content(
				mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = Pet.class))
			)
		)
	})
	public ResponseEntity<List<Pet>> listByNomeAndRaca(
		@RequestParam @Parameter(description = "Nome do pet") String nome,
		@RequestParam @Parameter(description = "Raça do pet") String raca
	) {
		return ResponseEntity.ok(listPetsByNomeAndRacaUseCase.execute(nome, raca));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um pet a partir do seu ID")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Pet atualizado com sucesso",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = Pet.class)
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
			description = "Pet não encontrado",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = ValidationHandler.ErrorResponse.class)
			)
		)
	})
	public ResponseEntity<Pet> update(
		@PathVariable @Parameter(description = "ID do pet") UUID id,
		@RequestBody @Valid @Parameter(description = "Novos dados do pet") PetDto petDto
	) {
		return updatePetByIdUseCase.execute(id, petDto)
									.map(ResponseEntity::ok)
									.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um pet a partir do seu ID")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "204",
			description = "Pet excluído com sucesso"
		)
	})
	public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "ID do pet") UUID id) {
		deletePetByIdUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}
}
