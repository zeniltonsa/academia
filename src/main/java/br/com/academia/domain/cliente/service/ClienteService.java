package br.com.academia.domain.cliente.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.academia.core.mappers.ClienteMapper;
import br.com.academia.domain.cliente.dto.RequestCliente;
import br.com.academia.domain.cliente.dto.filter.FiltroCliente;
import br.com.academia.domain.cliente.dto.response.ClienteDTO;
import br.com.academia.domain.cliente.model.EntCliente;
import br.com.academia.domain.cliente.repository.ClienteRepository;
import br.com.academia.domain.exception.NaoEncontradoException;
import br.com.academia.events.PagamentoRecebidoEvent;
import br.com.academia.events.PagamentoRecebidoEventPublisher;

@Service
public class ClienteService {

	private final ClienteRepository repository;
	private final ClienteMapper mapper;

	private final PagamentoRecebidoEventPublisher publisher;

	public ClienteService(ClienteRepository repository, ClienteMapper mapper,
			PagamentoRecebidoEventPublisher publisher) {
		this.repository = repository;
		this.mapper = mapper;
		this.publisher = publisher;
	}

	@Transactional(readOnly = true)
	public List<ClienteDTO> clientes(FiltroCliente filtro) {
		return repository.filtrar(filtro).stream().map(mapper::toDto).toList();
	}

	@Transactional
	public void salvar(RequestCliente request) {
		var cliente = repository.save(EntCliente.from(request));

		if (request.isPagamentoRecebido()) {
			publisher.pagamentoRecebido(new PagamentoRecebidoEvent(cliente,request.getValorRecebido()));
		}
	}

	@Transactional
	public void atualizar(Long id, RequestCliente request) {
		EntCliente target = repository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Cliente não encontrado."));
		EntCliente source = EntCliente.from(request);
		mapper.copy(source, target);
		repository.saveAndFlush(target);
	}

	@Transactional
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}
