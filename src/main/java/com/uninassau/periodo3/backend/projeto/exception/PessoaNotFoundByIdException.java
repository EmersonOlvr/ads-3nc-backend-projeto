package com.uninassau.periodo3.backend.projeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PessoaNotFoundByIdException extends RuntimeException {

	private static final long serialVersionUID = -387771457993847367L;
	
	public PessoaNotFoundByIdException() {
		super("Não foi possível encontrar pessoa com o ID informado.");
	}

}
