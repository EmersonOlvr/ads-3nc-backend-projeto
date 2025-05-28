package com.uninassau.periodo3.backend.projeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PetNotFoundByIdException extends RuntimeException {

	private static final long serialVersionUID = 5316558885440695276L;

	public PetNotFoundByIdException() {
		super("Não foi possível encontrar nenhum pet com o ID informado.");
	}

}
