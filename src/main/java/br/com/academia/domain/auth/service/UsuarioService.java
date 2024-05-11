package br.com.academia.domain.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.academia.domain.auth.UsuarioRepository;
import br.com.academia.domain.auth.domain.EntUsuario;
import br.com.academia.domain.auth.domain.RequestUsuario;

@Service
public class UsuarioService {

	private final UsuarioRepository repository;
	private final PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public EntUsuario cadastrar(RequestUsuario request) {
		EntUsuario usuario = EntUsuario.from(request);

		EntUsuario usuarioExistente = repository.findByNome(usuario.getNome());

		if (usuarioExistente != null) {
			throw new Error("Usuário já existe");
		}

		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

		return repository.save(usuario);

	}
}
