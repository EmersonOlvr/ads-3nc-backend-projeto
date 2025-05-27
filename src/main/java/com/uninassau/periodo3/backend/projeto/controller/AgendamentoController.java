package com.uninassau.periodo3.backend.projeto.controller;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;
import com.uninassau.periodo3.backend.projeto.service.agendamento.CreateAgendamendoUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.DeleteAgendamentoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.FindAllAgendamentosUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.FindAgendamentoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.UpdateAgendamentoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.dto.AgendamentoDto;

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
    public ResponseEntity<Agendamento> create(@RequestBody AgendamentoDto agendamentoDto) {
        return ResponseEntity.ok(createAgendamendoUseCase.execute(agendamentoDto));
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> findAll() {
        List<Agendamento> agendamentos = findAllAgendamentosUseCase.execute();
        return ResponseEntity.status(HttpStatus.OK)
        					.body(agendamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> findById(@PathVariable UUID id) {
        return findAgendamentoByIdUseCase.execute(id)
							                .map(ResponseEntity::ok)
							                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> update(@PathVariable UUID id, @RequestBody AgendamentoDto agendamentoDto) {
        return updateAgendamentoByIdUseCase.execute(id, agendamentoDto)
							                .map(ResponseEntity::ok)
							                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteAgendamentoByIdUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
    
}
