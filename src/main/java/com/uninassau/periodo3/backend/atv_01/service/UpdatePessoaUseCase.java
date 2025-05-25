package com.uninassau.periodo3.backend.atv_01.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uninassau.periodo3.backend.atv_01.domain.Pessoa;
import com.uninassau.periodo3.backend.atv_01.repository.PessoaRepository;
import com.uninassau.periodo3.backend.atv_01.service.dto.PessoaDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpdatePessoaUseCase {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private FindPessoaByIdUseCase findPessoaByIdUseCase;

	public Pessoa execute(Long id, PessoaDto pessoaDto) {
		Pessoa pessoa = this.findPessoaByIdUseCase.execute(id);
		BeanUtils.copyProperties(pessoaDto, pessoa);
		
		log.info(String.format("Atualizando a pessoa com id %s...", id));
		this.pessoaRepository.save(pessoa);
		log.info("Pessoa atualizada com sucesso!");
		
		return pessoa;
	}
	
}
