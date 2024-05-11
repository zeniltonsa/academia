package br.com.academia.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.academia.domain.auth.domain.EntUsuario;
import br.com.academia.domain.auth.domain.RequestUsuario;
import br.com.academia.domain.auth.service.UsuarioService;
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
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public EntUsuario cadastrar(@Valid @RequestBody RequestUsuario request) {
		return service.cadastrar(request);
	}

}
