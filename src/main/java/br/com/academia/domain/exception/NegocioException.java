package br.com.academia.domain.exception;

public class NegocioException extends Exception {

	private static final long serialVersionUID = 7520209236229410550L;

	public NegocioException(String message) {
		super(message);
	}

	public NegocioException(String message, Exception ex) {
		super(message, ex);
	}
}
