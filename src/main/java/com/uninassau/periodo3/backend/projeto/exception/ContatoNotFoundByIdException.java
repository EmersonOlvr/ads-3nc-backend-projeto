package com.uninassau.periodo3.backend.projeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ContatoNotFoundByIdException extends RuntimeException {

	private static final long serialVersionUID = -4820600870120584925L;

	public ContatoNotFoundByIdException() {
		super("Não foi possível encontrar contato com o ID informado.");
	}

}
