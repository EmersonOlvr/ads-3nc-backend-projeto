package com.uninassau.periodo3.backend.projeto.exception;

import java.time.Instant;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@RestControllerAdvice
public class ValidationHandler {

	@AllArgsConstructor
	@Getter
	@Schema(description = "Erro em campo individual")
	public class FieldErrorResponse {

		@Schema(description = "Campo com erro", example = "title")
		private final String field;

		@Schema(description = "Mensagem de erro do campo", example = "O título é obrigatório.")
		private final String message;
	}

	@AllArgsConstructor
	@Getter
	@Schema(description = "Resposta para erros gerais")
	public class ErrorResponse {

		@Schema(description = "Data e hora do erro")
		private final Instant timestamp;

		@Schema(description = "Código HTTP", example = "404")
		private final int status;

		@Schema(description = "Motivo do erro")
		private final String error;

		@Schema(description = "Mensagem de erro geral", example = "Mensagem do erro")
		private final String message;

		@Schema(description = "Caminho da requisição", example = "/api/v1/example")
		private final String path;
	}

	@Getter
	@Schema(description = "Resposta para erro de validação (HTTP 400)")
	public class ValidationErrorResponse extends ErrorResponse {

		@Schema(description = "Lista de erros de campo")
		private final List<FieldErrorResponse> errors;
		
		public ValidationErrorResponse(
				Instant timestamp,
				int status,
				String error,
				String message,
				String path,
				List<FieldErrorResponse> errors
		) {
			super(timestamp, status, error, message, path);
			this.errors = errors;
		}
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
		List<FieldErrorResponse> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
																		.map(err -> new FieldErrorResponse(err.getField(), err.getDefaultMessage()))
																		.sorted(Comparator
																				.comparing(FieldErrorResponse::getField)
																				.thenComparing(FieldErrorResponse::getMessage)
																		)
																		.toList();

		ValidationErrorResponse response = new ValidationErrorResponse(
				Instant.now(),
				HttpStatus.BAD_REQUEST.value(), 
				HttpStatus.BAD_REQUEST.getReasonPhrase(),
				"Erro de validação nos campos", 
				request.getRequestURI(), 
				fieldErrors
		);

		return ResponseEntity.badRequest().body(response);
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	public final ResponseEntity<Object> handleNoHandleFoundExceptions(NoResourceFoundException ex, WebRequest request) {
		LinkedHashMap<String, Object> extra = new LinkedHashMap<>();
		extra.put("description", ex.getMessage());
		
		ErrorResponse response = new ErrorResponse(
				Instant.now(),
				ex.getStatusCode().value(), 
				ex.getBody().getTitle(),
				"O endpoint solicitado não existe.", 
				request.getDescription(false).substring(4)
		);

		return ResponseEntity.status(ex.getStatusCode().value())
							.body(response);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public final ResponseEntity<Object> handleResponseStatusExceptions(ResponseStatusException ex, WebRequest request) {
		ErrorResponse response = new ErrorResponse(
				Instant.now(),
				ex.getStatusCode().value(),
				ex.getBody().getTitle(),
				ex.getReason(),
				request.getDescription(false).substring(4)
		);
		
		return ResponseEntity.status(ex.getStatusCode().value())
							.body(response);
	}
	
	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		HttpStatus status = this.determineHttpStatus(ex);
		ex.printStackTrace();
		
		ErrorResponse response = new ErrorResponse(
				Instant.now(),
				status.value(), 
				status.getReasonPhrase(),
				ex.getMessage(), 
				request.getDescription(false).substring(4)
		);
		
		return ResponseEntity.status(status.value())
							.body(response);
	}

	private HttpStatus determineHttpStatus(Exception ex) {
		ResponseStatus annotation = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
		return (annotation != null) ? annotation.code() : HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
}