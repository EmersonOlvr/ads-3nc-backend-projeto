package com.uninassau.periodo3.backend.atv_01.agendamento.repository;

import com.uninassau.periodo3.backend.atv_01.agendamento.domain.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {
}
