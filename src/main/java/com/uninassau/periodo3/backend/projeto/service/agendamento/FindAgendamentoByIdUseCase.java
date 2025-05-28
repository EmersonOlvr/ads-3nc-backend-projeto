package com.uninassau.periodo3.backend.projeto.service.agendamento;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;
import com.uninassau.periodo3.backend.projeto.exception.AgendamentoNotFoundByIdException;
import com.uninassau.periodo3.backend.projeto.repository.AgendamentoRepository;

@Service
public class FindAgendamentoByIdUseCase {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	public Agendamento execute(UUID id) {
		return agendamentoRepository.findById(id)
									.orElseThrow(AgendamentoNotFoundByIdException::new);
	}
	
}
