package com.uninassau.periodo3.backend.projeto.service.contato;

import com.uninassau.periodo3.backend.projeto.domain.Contato;
import com.uninassau.periodo3.backend.projeto.repository.ContatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindContatoByIdUseCase {

    @Autowired
    private ContatoRepository contatoRepository;

    public Optional<Contato> execute(UUID id) {
        return contatoRepository.findById(id);
    }
    
}
