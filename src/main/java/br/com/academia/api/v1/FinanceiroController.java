package br.com.academia.api.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.academia.domain.financeiro.RequestMensalidade;
import br.com.academia.domain.financeiro.dto.MensalidadeDTO;
import br.com.academia.domain.financeiro.service.FinanceiroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Financeiro", description = "Gerenciamento financeiro dos alunos")
@RestController
@RequestMapping("/v1/financeiro")
public class FinanceiroController {

	private final FinanceiroService service;

	public FinanceiroController(FinanceiroService service) {
		this.service = service;
	}

	@Operation(summary = "Lista mensalidades", description = "Lista todas as mensalidades")
	@PreAuthorize("hasRole('MENSALIDADE_LISTAR')")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MensalidadeDTO> mensalidades() {
		return service.mensalidades();
	}

	@Operation(summary = "Cadastrar mensalidade", description = "Cadastra uma mensalidade de um aluno")
	@PreAuthorize("hasRole('MENSALIDADE_CADASTRAR')")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public void salvar(@Valid @RequestBody RequestMensalidade request) {
		service.salvar(request);
	}

	@Operation(summary = "Atualizar mensalidade", description = "Atualiza uma mensalidade de um aluno")
	@PreAuthorize("hasRole('MENSALIDADE_ATUALIZAR')")
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping
	public void atualizar(@Valid @RequestBody RequestMensalidade request) {
		service.atualizar(request);
	}

	@Operation(summary = "Excluir mensalidade", description = "Exclui uma mensalidade de um aluno")
	@PreAuthorize("hasRole('MENSALIDADE_DELETAR')")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}

}