package com.uninassau.periodo3.backend.projeto.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {
	
	boolean existsByData(LocalDateTime startDate);
	
}
