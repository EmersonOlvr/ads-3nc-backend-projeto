package com.uninassau.periodo3.backend.projeto.service.agendamento;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;
import com.uninassau.periodo3.backend.projeto.exception.AgendamentoAlreadyExistsException;
import com.uninassau.periodo3.backend.projeto.repository.AgendamentoRepository;
import com.uninassau.periodo3.backend.projeto.service.agendamento.dto.AgendamentoDto;

class CreateAgendamentoUseCaseTest {
	
	@Mock
	private AgendamentoRepository agendamentoRepository;

	@InjectMocks
	private CreateAgendamendoUseCase createAgendamentoUseCase;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveCriarAgendamentoComSucesso() {
		LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
		AgendamentoDto dto = new AgendamentoDto("Agendamento X", "usuario@email.com", "81988884444", LocalDateTime.now());
		Agendamento agendamento = new Agendamento();

		when(agendamentoRepository.existsByData(now)).thenReturn(false);
		when(agendamentoRepository.save(any())).thenReturn(agendamento);

		Agendamento result = createAgendamentoUseCase.execute(dto);

		assertNotNull(result);
	}

	@Test
	void deveDarErroQuandoAgendamentoJaExistir() {
		LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
		AgendamentoDto dto = new AgendamentoDto("Agendamento X", "usuario@email.com", "81988884444", LocalDateTime.now());

		when(agendamentoRepository.existsByData(now)).thenReturn(true);

		assertThrows(AgendamentoAlreadyExistsException.class, () -> createAgendamentoUseCase.execute(dto));
	}
	
}