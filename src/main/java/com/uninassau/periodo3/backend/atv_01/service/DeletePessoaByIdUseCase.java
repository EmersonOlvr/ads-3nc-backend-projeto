package com.uninassau.periodo3.backend.atv_01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uninassau.periodo3.backend.atv_01.repository.PessoaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeletePessoaByIdUseCase {

	@Autowired
	private PessoaRepository pessoaRepository;

	public void execute(Long id) {
		log.info(String.format("Excluindo a pessoa com id %s (caso exista)...", id));
		this.pessoaRepository.deleteById(id);
	}

}
