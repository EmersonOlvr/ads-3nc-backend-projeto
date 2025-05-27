package com.uninassau.periodo3.backend.projeto.service.contato;

import com.uninassau.periodo3.backend.projeto.domain.Contato;
import com.uninassau.periodo3.backend.projeto.repository.ContatoRepository;
import com.uninassau.periodo3.backend.projeto.service.contato.dto.ContatoDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateContatoByIdUseCase {

    @Autowired
    private ContatoRepository contatoRepository;

    public Optional<Contato> execute(UUID id, ContatoDto attContato) {
        return contatoRepository.findById(id).map(contatoExistente -> {
            BeanUtils.copyProperties(attContato, contatoExistente, "id");

            return contatoRepository.save(contatoExistente);
        });
    }
    
}
