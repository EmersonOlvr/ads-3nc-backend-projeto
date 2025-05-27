package com.uninassau.periodo3.backend.projeto.service.agendamento;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;
import com.uninassau.periodo3.backend.projeto.repository.AgendamentoRepository;
import com.uninassau.periodo3.backend.projeto.service.agendamento.dto.AgendamentoDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAgendamendoUseCase {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Agendamento execute(AgendamentoDto agendamentoDto) {
        Agendamento newAgendamento = new Agendamento();
        BeanUtils.copyProperties(agendamentoDto, newAgendamento);

        return agendamentoRepository.save(newAgendamento);
    }
}
