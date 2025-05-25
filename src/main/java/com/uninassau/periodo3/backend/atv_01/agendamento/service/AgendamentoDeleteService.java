package com.uninassau.periodo3.backend.atv_01.agendamento.service;

import com.uninassau.periodo3.backend.atv_01.agendamento.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AgendamentoDeleteService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public void delete(UUID id) {
        agendamentoRepository.deleteById(id);
    }
}
