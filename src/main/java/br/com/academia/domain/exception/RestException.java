package br.com.academia.domain.exception;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7637795655200214618L;
	private final HttpStatus status;
	private final String message;

	public RestException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public RestException(HttpStatus status, String message, Throwable cause) {
		super(cause);
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
