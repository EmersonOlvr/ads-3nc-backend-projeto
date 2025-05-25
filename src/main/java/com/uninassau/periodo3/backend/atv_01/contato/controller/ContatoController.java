package com.uninassau.periodo3.backend.atv_01.contato.controller;

import com.uninassau.periodo3.backend.atv_01.contato.domain.Contato;
import com.uninassau.periodo3.backend.atv_01.contato.dto.ContatoDto;
import com.uninassau.periodo3.backend.atv_01.contato.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoCreateService contatoCreateService;

    @Autowired
    private ContatoFindAllService contatoFindAllService;

    @Autowired
    private ContatoFindByIdService contatoFindByIdService;

    @Autowired
    private ContatoUpdateService contatoUpdateService;

    @Autowired
    private ContatoDeleteService contatoDeleteService;

    @PostMapping
    public ResponseEntity<Contato> create(@RequestBody ContatoDto contatoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoCreateService.create(contatoDto));
    }

    @GetMapping
    public ResponseEntity<List<Contato>> findAll() {
        List<Contato> contatos = contatoFindAllService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> findById(@PathVariable UUID id) {
        return contatoFindByIdService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> update(@PathVariable UUID id, @RequestBody ContatoDto contatoDto) {
        return contatoUpdateService.update(id, contatoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        contatoDeleteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
