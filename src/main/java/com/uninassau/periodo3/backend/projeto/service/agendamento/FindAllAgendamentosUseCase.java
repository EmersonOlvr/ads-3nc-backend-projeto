package com.uninassau.periodo3.backend.projeto.service.agendamento;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;
import com.uninassau.periodo3.backend.projeto.repository.AgendamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllAgendamentosUseCase {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	public List<Agendamento> execute() {
		return agendamentoRepository.findAll();
	}

}
