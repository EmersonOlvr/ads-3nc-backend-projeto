package com.uninassau.periodo3.backend.atv_01.contato.service;

import com.uninassau.periodo3.backend.atv_01.contato.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContatoDeleteService {

    @Autowired
    private ContatoRepository contatoRepository;

    public void delete(UUID id) {
        contatoRepository.deleteById(id);
    }
}
