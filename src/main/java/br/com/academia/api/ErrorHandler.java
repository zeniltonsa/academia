package br.com.academia.api;

import static br.com.academia.api.ResponseMessages.ERRO_CONSULTA;
import static br.com.academia.api.ResponseMessages.ERRO_INTERNO;
import static br.com.academia.api.ResponseMessages.ERRO_REQUISICAO;
import static br.com.academia.api.ResponseMessages.VIOLACAO_INTEGRIDADE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class ErrorHandler {

	private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegratyViolation(Exception ex) {
		log.warn("HandleDataIntegratyViolation: ", ex.getCause());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(new ErrorResponse(VIOLACAO_INTEGRIDADE.getDescricao()));
	}

	@ExceptionHandler({ SQLException.class })
	public ResponseEntity<Object> handleSQLException(SQLException ex) {
		log.warn("HandleSQLException: ", ex.getCause());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(new ErrorResponse(ERRO_INTERNO.getDescricao()));
	}

	@ExceptionHandler({ JpaSystemException.class })
	public ResponseEntity<Object> handleJpaSystemException(JpaSystemException ex) {
		log.warn("HandleJpaSystemException: ", ex.getCause());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(new ErrorResponse(ERRO_INTERNO.getDescricao()));
	}

	@ExceptionHandler({ GenericJDBCException.class, TransactionException.class,
			CannotCreateTransactionException.class })
	public ResponseEntity<Object> handleGenericJDBCException(Exception ex) {
		log.warn("HandleGenericJDBCException: ", ex.getCause());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(new ErrorResponse(ERRO_INTERNO.getDescricao()));
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		log.warn("HandleMethodArgumentNotValid: ", ex.getCause());
		List<String> errorMessage = new ArrayList<>();

		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			String mensagem = fieldError.contains(PropertyAccessException.class) ? "Valor fornecido não é o esperado."
					: fieldError.getDefaultMessage();

			errorMessage.add(mensagem);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(ERRO_REQUISICAO.getDescricao(), errorMessage.toString()));
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		log.warn("HandleHttpMessageNotReadableException: ", ex.getCause());

		String errorMessage = "Valor informado é inválido.";
		if (ex.getCause() instanceof InvalidFormatException) {
			InvalidFormatException cause = (InvalidFormatException) ex.getCause();
			errorMessage = "Valor informado é inválido: '" + cause.getValue().toString() + "'.";
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessage));
	}

	@ExceptionHandler({ BindException.class })
	public ResponseEntity<Object> handleHttpMessageNotReadableException(BindException ex) {
		log.warn("HandleBindException: ", ex.getCause());

		List<String> errorMessage = new ArrayList<>();

		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			String mensagem = fieldError.contains(PropertyAccessException.class) ? "Valor fornecido não é o esperado."
					: fieldError.getDefaultMessage();

			errorMessage.add(mensagem);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(ERRO_REQUISICAO.getDescricao(), errorMessage.toString()));
	}

	public ResponseEntity<Object> handleInvalidDataAccessApiUsage(Exception ex) {
		log.warn("HandleInvalidDataAccessApiUsage: ", ex.getCause());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse(ERRO_CONSULTA.getDescricao()));
	}

	@ExceptionHandler({ InvalidDataAccessResourceUsageException.class })
	public ResponseEntity<Object> handleInvalidDataAccessResourceUsage(Exception ex) {
		log.warn("HandleInvalidDataAccessResourceUsage: ", ex.getCause());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ERRO_CONSULTA.getDescricao()));
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleException(Exception ex) {
		log.warn("HandleException: ", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
	}

}
