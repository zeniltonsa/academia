package br.com.academia.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ErrorResponse {

	private String mensagemExcecao;

	private String mensagemAoCliente;

	private Throwable excecao;

	public ErrorResponse(String mensagemAoCliente) {
		this.mensagemAoCliente = mensagemAoCliente;
	}
	
	public ErrorResponse(String mensagemAoCliente, String descricao) {
		this.mensagemAoCliente = mensagemAoCliente;
		this.mensagemExcecao = descricao;
	}

	public String getMensagemExcecao() {
		return mensagemExcecao;
	}

	public void setMensagemExcecao(String mensagemExcecao) {
		this.mensagemExcecao = mensagemExcecao;
	}

	public Throwable getExcecao() {
		return excecao;
	}

	public void setExcecao(Throwable excecao) {
		this.excecao = excecao;
	}

	public String getMensagemAoCliente() {
		return mensagemAoCliente;
	}

	public void setMensagemAoCliente(String mensagemAoCliente) {
		this.mensagemAoCliente = mensagemAoCliente;
	}

}
