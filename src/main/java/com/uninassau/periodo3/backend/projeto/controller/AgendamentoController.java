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
import com.uninassau.periodo3.backend.projeto.service.agendamento.CreateAgendamendoUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.DeleteAgendamentoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.FindAgendamentoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.FindAllAgendamentosUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.UpdateAgendamentoByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.agendamento.dto.AgendamentoDto;

import jakarta.validation.Valid;

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
    public ResponseEntity<Agendamento> create(@RequestBody @Valid AgendamentoDto agendamentoDto) {
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
        return ResponseEntity.ok(findAgendamentoByIdUseCase.execute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> update(@PathVariable UUID id, @RequestBody @Valid AgendamentoDto agendamentoDto) {
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
