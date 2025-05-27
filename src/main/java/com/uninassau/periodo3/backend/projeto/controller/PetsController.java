package com.uninassau.periodo3.backend.projeto.controller;

import com.uninassau.periodo3.backend.projeto.domain.Pets;
import com.uninassau.periodo3.backend.projeto.service.pets.DeletePetByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.FindPetByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.UpdatePetByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.CreatePetUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.FindAllPetsUseCase;
import com.uninassau.periodo3.backend.projeto.service.pets.dto.PetDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

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
    private UpdatePetByIdUseCase updatePetByIdUseCase;

    @Autowired
    private DeletePetByIdUseCase deletePetByIdUseCase;

    @PostMapping
    public ResponseEntity<Pets> create(@RequestBody PetDto petDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
        						.body(createPetUseCase.execute(petDto));
    }

    @GetMapping
    public ResponseEntity<List<Pets>> findAll() {
        List<Pets> pets = findAllPetsUseCase.execute();
        return ResponseEntity.status(HttpStatus.OK)
        						.body(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pets> findById(@PathVariable UUID id) {
        return findPetByIdUseCase.execute(id)
					                .map(ResponseEntity::ok)
					                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pets> update(@PathVariable UUID id, @RequestBody PetDto petDto) {
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
