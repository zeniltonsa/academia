package br.com.academia.api.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.academia.domain.cliente.dto.RequestCliente;
import br.com.academia.domain.cliente.dto.filter.FiltroCliente;
import br.com.academia.domain.cliente.dto.response.ClienteDTO;
import br.com.academia.domain.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clientes", description = "Gerenciamento de clientes")
@RestController
@RequestMapping("/v1/clientes")
public class ClientesController {

	private final ClienteService service;

	public ClientesController(ClienteService service) {
		this.service = service;
	}

	@Operation(summary = "Listar clientes", description = "Lista todos os clientes cadastrados")
	@PreAuthorize("hasRole('CLIENTE_LISTAR')")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClienteDTO> clientes(FiltroCliente filtro) {
		return service.clientes(filtro);
	}

	@Operation(summary = "Cadastrar cliente", description = "Cadastra um clientes")
	@PreAuthorize("hasRole('CLIENTE_CADASTRAR')")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public void salvar(@Valid @RequestBody RequestCliente request) {
		service.salvar(request);
	}

	@Operation(summary = "Atualizar cliente", description = "Atualiza um cliente")
	@PreAuthorize("hasRole('CLIENTE_ALTERAR')")
	@ResponseStatus(code = HttpStatus.OK)
	@PatchMapping("/{id}")
	public void atualizar(@PathVariable Long id, @Valid @RequestBody RequestCliente request) {
		service.atualizar(id, request);
	}

	@Operation(summary = "Excluir cliente", description = "Exclui um cliente")
	@PreAuthorize("hasRole('CLIENTE_DELETAR')")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}

}
