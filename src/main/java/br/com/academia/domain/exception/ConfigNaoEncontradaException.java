package br.com.academia.domain.exception;

public class ConfigNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6001507393552977342L;

	public ConfigNaoEncontradaException() {
		super("Config não encontrada.");
	}
}
