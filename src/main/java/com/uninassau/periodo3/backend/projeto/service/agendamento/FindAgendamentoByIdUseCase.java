package com.uninassau.periodo3.backend.projeto.service.agendamento;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;
import com.uninassau.periodo3.backend.projeto.repository.AgendamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindAgendamentoByIdUseCase {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	public Optional<Agendamento> execute(UUID id) {
		return agendamentoRepository.findById(id);
	}
	
}
