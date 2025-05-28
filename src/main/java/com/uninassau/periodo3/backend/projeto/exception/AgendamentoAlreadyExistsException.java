package com.uninassau.periodo3.backend.projeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AgendamentoAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 7713061241938198811L;

	public AgendamentoAlreadyExistsException() {
		super("Já existe um agendamento para o dia e horário informado, informe outra data/hora.");
	}

}
