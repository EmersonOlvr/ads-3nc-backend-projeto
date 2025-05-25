package com.uninassau.periodo3.backend.atv_01.agendamento.service;

import com.uninassau.periodo3.backend.atv_01.agendamento.domain.Agendamento;
import com.uninassau.periodo3.backend.atv_01.agendamento.dto.AgendamentoDto;
import com.uninassau.periodo3.backend.atv_01.agendamento.repository.AgendamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AgendamentoUpdateService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Optional<Agendamento> update(UUID id, AgendamentoDto attAgendamento) {
        return agendamentoRepository.findById(id).map(agendamentoExistente -> {
            BeanUtils.copyProperties(attAgendamento, agendamentoExistente, "id");

            return agendamentoRepository.save(agendamentoExistente);
        });
    }
}
