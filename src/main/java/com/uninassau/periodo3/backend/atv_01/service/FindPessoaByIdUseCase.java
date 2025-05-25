package com.uninassau.periodo3.backend.atv_01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uninassau.periodo3.backend.atv_01.domain.Pessoa;
import com.uninassau.periodo3.backend.atv_01.exception.PessoaNotFoundByIdException;
import com.uninassau.periodo3.backend.atv_01.repository.PessoaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FindPessoaByIdUseCase {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa execute(Long id) {
		log.info(String.format("Buscando pessoa com o id %s...", id));
		Pessoa pessoa = this.pessoaRepository.findById(id)
											.orElseThrow(PessoaNotFoundByIdException::new);
		
		log.info(String.format("Pessoa encontrada com sucesso: %s", pessoa.getNome()));
		
		return pessoa;
	}

}
