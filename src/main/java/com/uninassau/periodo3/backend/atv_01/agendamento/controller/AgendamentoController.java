package com.uninassau.periodo3.backend.atv_01.agendamento.controller;

import com.uninassau.periodo3.backend.atv_01.agendamento.domain.Agendamento;
import com.uninassau.periodo3.backend.atv_01.agendamento.dto.AgendamentoDto;
import com.uninassau.periodo3.backend.atv_01.agendamento.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamendoCreateService agendamendoCreateService;

    @Autowired
    private AgendamentoFindByIdService agendamendoFindByIdService;

    @Autowired
    private AgendamentoFindAllService agendamendoFindAllService;

    @Autowired
    private AgendamentoUpdateService agendamendoUpdateService;

    @Autowired
    private AgendamentoDeleteService agendamendoDeleteService;

    @PostMapping
    public ResponseEntity<Agendamento> create(@RequestBody AgendamentoDto agendamento) {
        return ResponseEntity.ok(agendamendoCreateService.create(agendamento));
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> findAll() {
        List<Agendamento> agendamentos = agendamendoFindAllService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(agendamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> findById(@PathVariable UUID id) {
        return agendamendoFindByIdService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> update(@PathVariable UUID id, @RequestBody AgendamentoDto agendamento) {
        return agendamendoUpdateService.update(id, agendamento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        agendamendoDeleteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
