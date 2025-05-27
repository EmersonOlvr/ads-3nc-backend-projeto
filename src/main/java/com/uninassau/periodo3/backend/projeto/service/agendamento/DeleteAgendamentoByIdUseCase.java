package com.uninassau.periodo3.backend.projeto.service.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uninassau.periodo3.backend.projeto.repository.AgendamentoRepository;

import java.util.UUID;

@Service
public class DeleteAgendamentoByIdUseCase {
	
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public void execute(UUID id) {
        agendamentoRepository.deleteById(id);
    }
    
}
