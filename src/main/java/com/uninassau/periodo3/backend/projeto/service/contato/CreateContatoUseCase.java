package com.uninassau.periodo3.backend.projeto.service.contato;

import com.uninassau.periodo3.backend.projeto.domain.Contato;
import com.uninassau.periodo3.backend.projeto.repository.ContatoRepository;
import com.uninassau.periodo3.backend.projeto.service.contato.dto.ContatoDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateContatoUseCase {

	@Autowired
	private ContatoRepository contatoRepository;

	public Contato execute(ContatoDto contatoDto) {
		Contato newContato = new Contato();
		BeanUtils.copyProperties(contatoDto, newContato);

		return contatoRepository.save(newContato);
	}
	
}
