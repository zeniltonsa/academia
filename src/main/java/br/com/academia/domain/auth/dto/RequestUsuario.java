package br.com.academia.domain.auth.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestUsuario {

	@NotBlank(message = "Login deve ser preenchido")
	private String login;

	@NotBlank(message = "Nome deve ser preenchido")
	private String nome;

	@NotBlank(message = "Senha deve ser preenchida")
	private String senha;

	private List<Long> permissoes = new ArrayList<>();

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Long> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Long> permissoes) {
		this.permissoes = permissoes;
	}

}
