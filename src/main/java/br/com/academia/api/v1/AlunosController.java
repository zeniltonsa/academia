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

import br.com.academia.domain.aluno.dto.RequestAluno;
import br.com.academia.domain.aluno.dto.response.AlunoDTO;
import br.com.academia.domain.aluno.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Alunos", description = "Gerenciamento de alunos")
@RestController
@RequestMapping("/v1/alunos")
public class AlunosController {

	private final AlunoService service;

	public AlunosController(AlunoService service) {
		this.service = service;
	}

	@Operation(summary = "Listar alunos", description = "Lista todos os alunos cadastrados")
	@PreAuthorize("hasRole('ALUNO_LISTAR')")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AlunoDTO> alunos() {
		return service.alunos();
	}

	@Operation(summary = "Cadastrar aluno", description = "Cadastra um aluno")
	@PreAuthorize("hasRole('ALUNO_CADASTRAR')")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public void salvar(@Valid @RequestBody RequestAluno request) {
		service.salvar(request);
	}

	@Operation(summary = "Atualizar aluno", description = "Atualiza um aluno")
	@PreAuthorize("hasRole('ALUNO_ALTERAR')")
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping
	public void atualizar(@Valid @RequestBody RequestAluno request) {
		service.atualizar(request);
	}

	@Operation(summary = "Excluir aluno", description = "Exclui um aluno")
	@PreAuthorize("hasRole('ALUNO_DELETAR')")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}

}
