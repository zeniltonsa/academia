package br.com.academia.domain.auth.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.academia.domain.auth.dto.RequestUsuario;
import br.com.academia.domain.auth.dto.RequestUsuarioV2;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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

	@Column(name = "LOGIN", length = 50, nullable = false, unique = true)
	private String login;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "SENHA", length = 200)
	private String senha;

	@ManyToMany
	@JoinTable(name = "TB_USUARIO_PERMISSAO", joinColumns = @JoinColumn(name = "USUARIO_ID"), inverseJoinColumns = @JoinColumn(name = "PERMISSAO_ID"))
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonIgnore
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
		usuario.setLogin(request.getLogin());
		usuario.setNome(request.getNome());
		usuario.setSenha(request.getSenha());

		return usuario;
	}

	public static EntUsuario from(RequestUsuarioV2 request) {
		var usuario = new EntUsuario();
		usuario.setNome(request.getNome());
		usuario.setSenha(request.getSenha());

		return usuario;
	}

}
