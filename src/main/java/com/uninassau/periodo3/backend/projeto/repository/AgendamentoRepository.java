package com.uninassau.periodo3.backend.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {

	boolean existsByDataBetween(LocalDateTime startDate, LocalDateTime endDate);
	
}
