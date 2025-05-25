package com.uninassau.periodo3.backend.atv_01.pets.controller;

import com.uninassau.periodo3.backend.atv_01.pets.domain.Pets;
import com.uninassau.periodo3.backend.atv_01.pets.dto.PetDto;
import com.uninassau.periodo3.backend.atv_01.pets.service.*;
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
    private PetsCreateService petsCreateService;

    @Autowired
    private PetsFindAllService petsFindAllService;

    @Autowired
    private PetFindByIdService petsFindByIdService;

    @Autowired
    private PetUpdateService petUpdateService;

    @Autowired
    private PetDeleteService petDeleteService;

    @PostMapping
    public ResponseEntity<Pets> create(@RequestBody PetDto pet) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petsCreateService.create(pet));
    }

    @GetMapping
    public ResponseEntity<List<Pets>> findAll() {
        List<Pets> pets = petsFindAllService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pets> findById(@PathVariable UUID id) {
        return petsFindByIdService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pets> update(@PathVariable UUID id, @RequestBody PetDto pet) {
        return petUpdateService.update(id, pet)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        petDeleteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
