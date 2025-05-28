package com.uninassau.periodo3.backend.projeto.service.agendamento;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;
import com.uninassau.periodo3.backend.projeto.repository.AgendamentoRepository;
import com.uninassau.periodo3.backend.projeto.service.agendamento.dto.AgendamentoDto;

class UpdateAgendamentoByIdUseCaseTest {

	@Mock
	private AgendamentoRepository agendamentoRepository;

	@InjectMocks
	private UpdateAgendamentoByIdUseCase updateUseCase;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveAtualizarAgendamentoSeExistir() {
		UUID id = UUID.randomUUID();
		Agendamento existing = new Agendamento();
		Agendamento updated = new Agendamento();
		AgendamentoDto dto = new AgendamentoDto("Agendamento X", "usuario@email.com", "81988884444", LocalDateTime.now());

		when(agendamentoRepository.findById(id)).thenReturn(Optional.of(existing));
		when(agendamentoRepository.save(any())).thenReturn(updated);

		Optional<Agendamento> result = updateUseCase.execute(id, dto);

		assertTrue(result.isPresent());
	}

	@Test
	void devRetornarVazioSeAgendamentoNaoExistir() {
		UUID id = UUID.randomUUID();
		AgendamentoDto dto = new AgendamentoDto("Agendamento X", "usuario@email.com", "81988884444", LocalDateTime.now());

		when(agendamentoRepository.findById(id)).thenReturn(Optional.empty());

		Optional<Agendamento> result = updateUseCase.execute(id, dto);

		assertFalse(result.isPresent());
	}
	
}