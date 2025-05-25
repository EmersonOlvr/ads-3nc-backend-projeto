package com.uninassau.periodo3.backend.atv_01.contato.service;

import com.uninassau.periodo3.backend.atv_01.contato.domain.Contato;
import com.uninassau.periodo3.backend.atv_01.contato.dto.ContatoDto;
import com.uninassau.periodo3.backend.atv_01.contato.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ContatoUpdateService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Optional<Contato> update(UUID id, ContatoDto attContato) {
        return contatoRepository.findById(id).map(contatoExistente -> {
            BeanUtils.copyProperties(attContato, contatoExistente, "id");

            return contatoRepository.save(contatoExistente);
        });
    }
}
