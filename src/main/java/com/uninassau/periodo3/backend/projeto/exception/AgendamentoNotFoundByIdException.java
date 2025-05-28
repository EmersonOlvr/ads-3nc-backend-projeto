package com.uninassau.periodo3.backend.projeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AgendamentoNotFoundByIdException extends RuntimeException {

	private static final long serialVersionUID = 6817523389086202172L;

	public AgendamentoNotFoundByIdException() {
		super("Não foi possível encontrar nenhum agendamento com o ID informado.");
	}

}
