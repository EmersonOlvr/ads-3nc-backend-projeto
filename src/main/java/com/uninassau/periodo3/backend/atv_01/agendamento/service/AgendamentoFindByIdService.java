package com.uninassau.periodo3.backend.atv_01.agendamento.service;

import com.uninassau.periodo3.backend.atv_01.agendamento.domain.Agendamento;
import com.uninassau.periodo3.backend.atv_01.agendamento.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AgendamentoFindByIdService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Optional<Agendamento> findById(UUID id) {return agendamentoRepository.findById(id); }
}
