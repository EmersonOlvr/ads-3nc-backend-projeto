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
import com.uninassau.periodo3.backend.projeto.service.pets.CreatePetUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.DeletePetByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.FindAllPetsUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.FindPetByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.ListPetsByNomeAndRacaUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.UpdatePetByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.dto.PetDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pets")
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
	public ResponseEntity<Pet> create(@RequestBody @Valid PetDto petDto) {
		return ResponseEntity.status(HttpStatus.CREATED)
							.body(createPetUseCase.execute(petDto));
	}

	@GetMapping
	public ResponseEntity<List<Pet>> findAll() {
		List<Pet> pets = findAllPetsUseCase.execute();
		return ResponseEntity.ok(pets);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pet> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(findPetByIdUseCase.execute(id));
	}

	@GetMapping("/buscaPorNomeERaca")
	public ResponseEntity<List<Pet>> listByNomeAndRaca(@RequestParam String nome, @RequestParam String raca) {
		return ResponseEntity.ok(listPetsByNomeAndRacaUseCase.execute(nome, raca));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pet> update(@PathVariable UUID id, @RequestBody @Valid PetDto petDto) {
		return updatePetByIdUseCase.execute(id, petDto)
									.map(ResponseEntity::ok)
									.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		deletePetByIdUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}

}
