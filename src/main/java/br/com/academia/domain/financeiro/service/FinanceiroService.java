package br.com.academia.domain.financeiro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.academia.core.mappers.MensalidadeMapper;
import br.com.academia.domain.cliente.repository.ClienteRepository;
import br.com.academia.domain.exception.NaoEncontradoException;
import br.com.academia.domain.financeiro.RequestMensalidade;
import br.com.academia.domain.financeiro.dto.MensalidadeDTO;
import br.com.academia.domain.financeiro.model.EntMensalidade;
import br.com.academia.domain.financeiro.repository.MensalidadeRepository;

@Service
public class FinanceiroService {

	private final MensalidadeRepository mensalidadeRepository;
	private final ClienteRepository clienteRepository;

	private final MensalidadeMapper mapper;

	public FinanceiroService(MensalidadeRepository repository, ClienteRepository clienteRepository,
			MensalidadeMapper mapper) {
		this.mensalidadeRepository = repository;
		this.clienteRepository = clienteRepository;
		this.mapper = mapper;
	}

	public List<MensalidadeDTO> mensalidades() {
		return mensalidadeRepository.findAll().stream().map(MensalidadeDTO::from).toList();
	}

	public void salvar(RequestMensalidade request) {
		var cliente = clienteRepository.findById(request.getCodigoCliente())
				.orElseThrow(() -> new NaoEncontradoException("Cliente não encontrado."));

		mensalidadeRepository.save(EntMensalidade.from(request, cliente));
	}

	public void atualizar(RequestMensalidade request) {
		EntMensalidade target = mensalidadeRepository.findById(request.getCodigo())
				.orElseThrow(() -> new NaoEncontradoException("Mensalidade não encontrada."));
		EntMensalidade source = EntMensalidade.from(request, target.getCliente());
		mapper.copy(source, target);
		mensalidadeRepository.saveAndFlush(target);
	}

	public void excluir(Long id) {
		mensalidadeRepository.deleteById(id);
	}

}
