package com.uninassau.periodo3.backend.projeto.service.agendamento;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;
import com.uninassau.periodo3.backend.projeto.exception.AgendamentoAlreadyExistsException;
import com.uninassau.periodo3.backend.projeto.repository.AgendamentoRepository;
import com.uninassau.periodo3.backend.projeto.service.agendamento.dto.AgendamentoDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateAgendamendoUseCase {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Agendamento execute(AgendamentoDto agendamentoDto) {
    	LocalDateTime startDate = agendamentoDto.data();
    	LocalDateTime endDate = startDate.plusMinutes(30);
    	
    	log.info("Verificando se o horário está disponível para agendamento...");
    	if (agendamentoRepository.existsByDataBetween(startDate, endDate))
    		throw new AgendamentoAlreadyExistsException();
    	
        Agendamento newAgendamento = new Agendamento();
        BeanUtils.copyProperties(agendamentoDto, newAgendamento);

        return agendamentoRepository.save(newAgendamento);
    }
}
