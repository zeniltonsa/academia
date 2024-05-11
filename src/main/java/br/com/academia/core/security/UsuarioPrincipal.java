package br.com.academia.core.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.academia.domain.auth.domain.EntUsuario;

public class UsuarioPrincipal {

	private String username;
	private String password;
	private List<SimpleGrantedAuthority> authorities;

	public UsuarioPrincipal(EntUsuario usuario) {
		this.username = usuario.getNome();
		this.password = usuario.getSenha();

		this.authorities = usuario.getPermissoes().stream()
				.map(permissao -> new SimpleGrantedAuthority("ROLE_".concat(permissao.getDescricao()))).toList();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<SimpleGrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public static UsuarioPrincipal criar(EntUsuario usuario) {
		return new UsuarioPrincipal(usuario);
	}

}
