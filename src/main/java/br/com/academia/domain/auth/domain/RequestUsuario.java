package br.com.academia.domain.auth.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestUsuario {

	@NotBlank(message = "Nome deve ser preenchido")
	private String nome;

	@NotBlank(message = "Senha deve ser preenchida")
	private String senha;

	private List<EntPermissao> permissoes = new ArrayList<EntPermissao>();

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<EntPermissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<EntPermissao> permissoes) {
		this.permissoes = permissoes;
	}

}
