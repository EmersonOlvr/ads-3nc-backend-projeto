package com.uninassau.periodo3.backend.atv_01.contato.service;

import com.uninassau.periodo3.backend.atv_01.contato.domain.Contato;
import com.uninassau.periodo3.backend.atv_01.contato.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoFindAllService {

    @Autowired
    private ContatoRepository contatoRepository;

    public List<Contato> findAll() {
        return contatoRepository.findAll();
    }
}
