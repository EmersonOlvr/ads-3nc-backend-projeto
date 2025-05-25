package com.uninassau.periodo3.backend.atv_01.agendamento.service;

import com.uninassau.periodo3.backend.atv_01.agendamento.domain.Agendamento;
import com.uninassau.periodo3.backend.atv_01.agendamento.dto.AgendamentoDto;
import com.uninassau.periodo3.backend.atv_01.agendamento.repository.AgendamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamendoCreateService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Agendamento create(AgendamentoDto agendamentoDto) {
        Agendamento newAgendamento = new Agendamento();
        BeanUtils.copyProperties(agendamentoDto, newAgendamento);

        return agendamentoRepository.save(newAgendamento);
    }
}
