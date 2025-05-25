package com.uninassau.periodo3.backend.atv_01.agendamento.service;

import com.uninassau.periodo3.backend.atv_01.agendamento.domain.Agendamento;
import com.uninassau.periodo3.backend.atv_01.agendamento.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoFindAllService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> findAll() {
        return agendamentoRepository.findAll();
    }
}
