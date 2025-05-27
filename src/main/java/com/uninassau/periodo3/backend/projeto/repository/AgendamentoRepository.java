package com.uninassau.periodo3.backend.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;

import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {
	
}
