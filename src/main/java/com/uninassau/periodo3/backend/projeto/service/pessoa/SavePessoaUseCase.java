package com.uninassau.periodo3.backend.projeto.service.pessoa;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uninassau.periodo3.backend.projeto.domain.Pessoa;
import com.uninassau.periodo3.backend.projeto.repository.PessoaRepository;
import com.uninassau.periodo3.backend.projeto.service.pessoa.dto.PessoaDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SavePessoaUseCase {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa execute(PessoaDto pessoaDto) {
		Pessoa pessoa = new Pessoa();
		BeanUtils.copyProperties(pessoaDto, pessoa);
		
		log.info(String.format("Salvando a pessoa \"%s\"...", pessoaDto.nome()));
		this.pessoaRepository.save(pessoa);
		log.info("Pessoa salva com sucesso!");
		
		return pessoa;
	}
	
}
