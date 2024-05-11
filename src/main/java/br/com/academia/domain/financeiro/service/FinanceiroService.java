package br.com.academia.domain.financeiro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.academia.core.mappers.MensalidadeMapper;
import br.com.academia.domain.aluno.repository.AlunoRepository;
import br.com.academia.domain.exception.NaoEncontradoException;
import br.com.academia.domain.financeiro.RequestMensalidade;
import br.com.academia.domain.financeiro.dto.MensalidadeDTO;
import br.com.academia.domain.financeiro.model.EntMensalidade;
import br.com.academia.domain.financeiro.repository.MensalidadeRepository;

@Service
public class FinanceiroService {

	private final MensalidadeRepository mensalidadeRepository;
	private final AlunoRepository alunoRepository;

	private final MensalidadeMapper mapper;

	public FinanceiroService(MensalidadeRepository repository, AlunoRepository alunoRepository,
			MensalidadeMapper mapper) {
		this.mensalidadeRepository = repository;
		this.alunoRepository = alunoRepository;
		this.mapper = mapper;
	}

	public List<MensalidadeDTO> mensalidades() {
		return mensalidadeRepository.findAll().stream().map(MensalidadeDTO::from).toList();
	}

	public void salvar(RequestMensalidade request) {
		var aluno = alunoRepository.findById(request.getCodigoAluno())
				.orElseThrow(() -> new NaoEncontradoException("Aluno não encontrado."));

		mensalidadeRepository.save(EntMensalidade.from(request, aluno));
	}

	public void atualizar(RequestMensalidade request) {
		EntMensalidade target = mensalidadeRepository.findById(request.getCodigo())
				.orElseThrow(() -> new NaoEncontradoException("Mensalidade não encontrada."));
		EntMensalidade source = EntMensalidade.from(request, target.getAluno());
		mapper.copy(source, target);
		mensalidadeRepository.saveAndFlush(target);
	}

	public void excluir(Long id) {
		mensalidadeRepository.deleteById(id);
	}

}
