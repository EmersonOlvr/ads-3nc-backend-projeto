package com.uninassau.periodo3.backend.projeto.service.agendamento;

import com.uninassau.periodo3.backend.projeto.domain.Agendamento;
import com.uninassau.periodo3.backend.projeto.repository.AgendamentoRepository;
import com.uninassau.periodo3.backend.projeto.service.agendamento.dto.AgendamentoDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateAgendamentoByIdUseCase {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Optional<Agendamento> execute(UUID id, AgendamentoDto attAgendamento) {
        return agendamentoRepository.findById(id).map(agendamentoExistente -> {
            BeanUtils.copyProperties(attAgendamento, agendamentoExistente, "id");

            return agendamentoRepository.save(agendamentoExistente);
        });
    }
}
