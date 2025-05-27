package com.uninassau.periodo3.backend.projeto.service.contato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uninassau.periodo3.backend.projeto.repository.ContatoRepository;

import java.util.UUID;

@Service
public class DeleteContatoByIdUseCase {

    @Autowired
    private ContatoRepository contatoRepository;

    public void execute(UUID id) {
        contatoRepository.deleteById(id);
    }
    
}
