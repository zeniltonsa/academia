package br.com.academia.domain.auth.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")
public class EntUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7940529008638995763L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 100, nullable = false)
	private Long id;

	@Column(name = "NOME", length = 50, nullable = false, unique = true)
	private String nome;

	@Column(name = "SENHA", length = 200)
	private String senha;

	@ManyToMany
	private List<EntPermissao> permissoes;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public static EntUsuario from(RequestUsuario request) {
		var usuario = new EntUsuario();
		usuario.setNome(request.getNome());
		usuario.setSenha(request.getSenha());
		usuario.setPermissoes(request.getPermissoes());

		return usuario;
	}

}
