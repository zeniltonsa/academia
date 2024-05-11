package br.com.academia.domain.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.academia.core.mappers.UsuarioMapper;
import br.com.academia.domain.auth.domain.EntPermissao;
import br.com.academia.domain.auth.domain.EntUsuario;
import br.com.academia.domain.auth.dto.RequestUsuario;
import br.com.academia.domain.auth.dto.RequestUsuarioV2;
import br.com.academia.domain.auth.repository.PermissaoRepository;
import br.com.academia.domain.auth.repository.UsuarioRepository;
import br.com.academia.domain.exception.NaoEncontradoException;
import br.com.academia.domain.exception.NegocioException;
import jakarta.validation.Valid;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PermissaoRepository permissaoRepository;
	private final PasswordEncoder passwordEncoder;

	private final UsuarioMapper mapper;

	public UsuarioService(UsuarioRepository usuarioRepository, PermissaoRepository permissaoRepository,
			PasswordEncoder passwordEncoder, UsuarioMapper mapper) {
		this.usuarioRepository = usuarioRepository;
		this.permissaoRepository = permissaoRepository;
		this.passwordEncoder = passwordEncoder;
		this.mapper = mapper;
	}

	@Transactional
	public EntUsuario cadastrar(RequestUsuario request) throws NegocioException {
		EntUsuario usuario = EntUsuario.from(request);

		EntUsuario usuarioExistente = usuarioRepository.findByLogin(usuario.getLogin());

		if (usuarioExistente != null) {
			throw new NegocioException("Usuário já existe");
		}

		usuario.setPermissoes(buscarPermissoes(request.getPermissoes()));
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

		return usuarioRepository.save(usuario);

	}

	@Transactional(readOnly = true)
	public EntUsuario buscarUsuario(String login) {
		EntUsuario usuarioExistente = usuarioRepository.findByLoginFetchPermissoes(login);

		if (usuarioExistente == null)
			throw new NaoEncontradoException("Usuário não encontrado.");

		return usuarioExistente;
	}

	@Transactional
	public EntUsuario atualizar(Long codigo, @Valid RequestUsuarioV2 request) {
		EntUsuario usuarioExistente = usuarioRepository.findById(codigo)
				.orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado."));

		EntUsuario source = EntUsuario.from(request);
		mapper.copy(source, usuarioExistente);
		usuarioExistente.setPermissoes(buscarPermissoes(request.getPermissoes()));
		usuarioExistente.setSenha(passwordEncoder.encode(request.getSenha()));

		return usuarioRepository.saveAndFlush(usuarioExistente);
	}

	private List<EntPermissao> buscarPermissoes(List<Long> codigoPermissoes) {
		List<EntPermissao> permissoes = new ArrayList<>();

		codigoPermissoes.forEach(permissao -> permissoes.add(permissaoRepository.findById(permissao)
				.orElseThrow(() -> new NaoEncontradoException("Permissão não encontrada."))));

		return permissoes;
	}
}
