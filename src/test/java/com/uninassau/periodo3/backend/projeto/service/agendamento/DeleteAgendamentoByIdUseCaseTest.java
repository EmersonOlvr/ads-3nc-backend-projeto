package com.uninassau.periodo3.backend.projeto.service.agendamento;

import static org.mockito.Mockito.*;

import java.util.UUID;

import com.uninassau.periodo3.backend.projeto.repository.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DeleteAgendamentoByIdUseCaseTest {
	
	@Mock
	private AgendamentoRepository agendamentoRepository;

	@InjectMocks
	private DeleteAgendamentoByIdUseCase deleteUseCase;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveExcluirPorId() {
		UUID id = UUID.randomUUID();

		deleteUseCase.execute(id);

		verify(agendamentoRepository, times(1)).deleteById(id);
	}
	
}