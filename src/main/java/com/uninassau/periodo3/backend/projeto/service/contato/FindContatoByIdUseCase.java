package com.uninassau.periodo3.backend.projeto.service.contato;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uninassau.periodo3.backend.projeto.domain.Contato;
import com.uninassau.periodo3.backend.projeto.exception.ContatoNotFoundByIdException;
import com.uninassau.periodo3.backend.projeto.repository.ContatoRepository;

@Service
public class FindContatoByIdUseCase {

	@Autowired
	private ContatoRepository contatoRepository;

	public Contato execute(UUID id) {
		return contatoRepository.findById(id)
								.orElseThrow(ContatoNotFoundByIdException::new);
	}
	
}
