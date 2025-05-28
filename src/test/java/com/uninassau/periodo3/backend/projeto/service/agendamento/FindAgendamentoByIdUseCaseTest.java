package com.uninassau.periodo3.backend.projeto.service.agendamento;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;
import com.uninassau.periodo3.backend.projeto.exception.AgendamentoNotFoundByIdException;
import com.uninassau.periodo3.backend.projeto.repository.AgendamentoRepository;

class FindAgendamentoByIdUseCaseTest {
	
	@Mock
	private AgendamentoRepository agendamentoRepository;

	@InjectMocks
	private FindAgendamentoByIdUseCase findUseCase;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveRetornarAgendamento() {
		UUID id = UUID.randomUUID();
		Agendamento agendamento = new Agendamento();
		when(agendamentoRepository.findById(id)).thenReturn(Optional.of(agendamento));

		Agendamento result = findUseCase.execute(id);

		assertNotNull(result);
	}

	@Test
	void deveDarErroSeNaoExistirPorId() {
		UUID id = UUID.randomUUID();
		when(agendamentoRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(AgendamentoNotFoundByIdException.class, () -> findUseCase.execute(id));
	}
	
}
