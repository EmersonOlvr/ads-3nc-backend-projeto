package com.uninassau.periodo3.backend.atv_01.contato.service;

import com.uninassau.periodo3.backend.atv_01.contato.domain.Contato;
import com.uninassau.periodo3.backend.atv_01.contato.dto.ContatoDto;
import com.uninassau.periodo3.backend.atv_01.contato.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoCreateService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato create(ContatoDto contatoDto) {
        Contato newContato = new Contato();
        BeanUtils.copyProperties(contatoDto, newContato);

        return contatoRepository.save(newContato);
    }
}
