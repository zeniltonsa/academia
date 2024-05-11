package br.com.academia.domain.aluno.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.academia.core.mappers.AlunoMapper;
import br.com.academia.domain.aluno.dto.RequestAluno;
import br.com.academia.domain.aluno.dto.response.AlunoDTO;
import br.com.academia.domain.aluno.model.EntAluno;
import br.com.academia.domain.aluno.repository.AlunoRepository;
import br.com.academia.domain.exception.NaoEncontradoException;

@Service
public class AlunoService {

	private final AlunoRepository repository;
	private final AlunoMapper mapper;

	public AlunoService(AlunoRepository repository, AlunoMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Transactional(readOnly = true)
	public List<AlunoDTO> alunos() {
		return repository.findAll().stream().map(AlunoDTO::from).toList();
	}

	@Transactional
	public void salvar(RequestAluno request) {
		repository.save(EntAluno.from(request));
	}

	@Transactional
	public void atualizar(RequestAluno request) {
		EntAluno target = repository.findById(request.getCodigo())
				.orElseThrow(() -> new NaoEncontradoException("Aluno não encontrado."));
		EntAluno source = EntAluno.from(request);
		mapper.copy(source, target);
		repository.saveAndFlush(target);
	}

	@Transactional
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}
