package com.uninassau.periodo3.backend.projeto.service.contato;

import com.uninassau.periodo3.backend.projeto.domain.Contato;
import com.uninassau.periodo3.backend.projeto.repository.ContatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllContatosUseCase {

	@Autowired
	private ContatoRepository contatoRepository;

	public List<Contato> execute() {
		return contatoRepository.findAll();
	}
	
}
