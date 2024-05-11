package br.com.academia.domain.exception;

import org.springframework.http.HttpStatus;

public class NaoEncontradoException extends RestException {

	private static final long serialVersionUID = 6718946249438563870L;

	public NaoEncontradoException() {
		super(HttpStatus.NOT_FOUND, "Recurso não encontrado.");
	}

	public NaoEncontradoException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

	public NaoEncontradoException(String message, Exception ex) {
		super(HttpStatus.NO_CONTENT, message, ex);
	}
}
