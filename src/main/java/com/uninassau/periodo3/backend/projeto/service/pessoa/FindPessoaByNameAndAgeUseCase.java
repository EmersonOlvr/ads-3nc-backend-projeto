package com.uninassau.periodo3.backend.projeto.service.pessoa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uninassau.periodo3.backend.projeto.domain.Pessoa;
import com.uninassau.periodo3.backend.projeto.repository.PessoaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FindPessoaByNameAndAgeUseCase {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> execute(String nome, Integer idade) {
		LocalDate birthDateOfTheGivenAge = LocalDate.now().minusYears(idade);
		
		log.info(String.format(
				"Buscando pessoas com o primeiro nome \"%s\" e que tenha pelo menos %s anos (nascidos antes de %s)...", 
				nome, idade, birthDateOfTheGivenAge
		));
		List<Pessoa> pessoas = this.pessoaRepository.findByNomeStartingWithAndDataNascimentoLessThanEqual(nome, birthDateOfTheGivenAge);
		log.info(String.format("%s pessoa(s) encontrada(s)", pessoas.size()));
		
		return pessoas;
	}
	
}
