package com.uninassau.periodo3.backend.projeto.service.agendamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;
import com.uninassau.periodo3.backend.projeto.repository.AgendamentoRepository;

class FindAllAgendamentosUseCaseTest {
	
	@Mock
	private AgendamentoRepository agendamentoRepository;

	@InjectMocks
	private FindAllAgendamentosUseCase findAllUseCase;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveRetornarTodosOsAgendamentos() {
		List<Agendamento> lista = Arrays.asList(new Agendamento(), new Agendamento());
		when(agendamentoRepository.findAll()).thenReturn(lista);

		List<Agendamento> result = findAllUseCase.execute();

		assertEquals(2, result.size());
	}
	
}