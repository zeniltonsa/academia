package br.com.academia.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.academia.domain.auth.domain.EntUsuario;
import br.com.academia.domain.auth.dto.RequestUsuario;
import br.com.academia.domain.auth.dto.RequestUsuarioV2;
import br.com.academia.domain.auth.service.UsuarioService;
import br.com.academia.domain.exception.NegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuários", description = "Gerenciamento de usuários")
@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {

	private final UsuarioService service;

	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	@Operation(summary = "Cadastrar usuário", description = "Cadastra um usuário")
	@PreAuthorize("hasRole('USUARIO_CADASTRAR')")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public EntUsuario cadastrar(@Valid @RequestBody RequestUsuario request) throws NegocioException {
		return service.cadastrar(request);
	}

	@Operation(summary = "Buscar usuário", description = "Busca um usuário")
	@PreAuthorize("hasRole('USUARIO_LISTAR')")
	@GetMapping("/{login}")
	public EntUsuario buscarUsuario(@PathVariable String login) {
		return service.buscarUsuario(login);
	}

	@Operation(summary = "Ataulizar usuário", description = "Atualiza um usuário")
	@PreAuthorize("hasRole('USUARIO_ALTERAR')")
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/{codigo}")
	public EntUsuario atualizar(@PathVariable Long codigo, @Valid @RequestBody RequestUsuarioV2 request) {
		return service.atualizar(codigo, request);
	}

}
