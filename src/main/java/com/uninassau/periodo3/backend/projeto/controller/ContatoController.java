package com.uninassau.periodo3.backend.projeto.controller;

import com.uninassau.periodo3.backend.projeto.domain.Contato;
import com.uninassau.periodo3.backend.projeto.service.contato.CreateContatoUseCase;
import com.uninassau.periodo3.backend.projeto.service.contato.DeleteContatoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.contato.FindAllContatosUseCase;
import com.uninassau.periodo3.backend.projeto.service.contato.FindContatoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.contato.UpdateContatoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.contato.dto.ContatoDto;

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
    public ResponseEntity<Contato> create(@RequestBody ContatoDto contatoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createContatoUseCase.execute(contatoDto));
    }

    @GetMapping
    public ResponseEntity<List<Contato>> findAll() {
        List<Contato> contatos = findAllContatosUseCase.execute();
        return ResponseEntity.status(HttpStatus.OK)
        					.body(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> findById(@PathVariable UUID id) {
        return findContatoByIdUseCase.execute(id)
						                .map(ResponseEntity::ok)
						                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> update(@PathVariable UUID id, @RequestBody ContatoDto contatoDto) {
        return updateContatoByIdUseCase.execute(id, contatoDto)
						                .map(ResponseEntity::ok)
						                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteContatoByIdUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
    
}
