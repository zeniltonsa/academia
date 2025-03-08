package br.com.academia.events;

import java.time.LocalDate;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.academia.domain.cliente.model.EntCliente;
import br.com.academia.domain.financeiro.model.EntMensalidade;
import br.com.academia.domain.financeiro.repository.MensalidadeRepository;

@Component
public class PagamentoRecebidoEventListener {

	private final MensalidadeRepository repository;

	public PagamentoRecebidoEventListener(MensalidadeRepository repository) {
		this.repository = repository;
	}

	@EventListener
	@Transactional
	public void pagamentoRecebido(PagamentoRecebidoEvent event) {
		var cliente = (EntCliente) event.getSource();
		var valorRecebido = event.getValorRecebido();
		var dataVencimento = cliente.getDataCadastro().plusMonths(1).toLocalDate();

		var mensalidade = EntMensalidade.builder() //
				.dataPagamento(LocalDate.now()) //
				.dataVencimento(dataVencimento) //
				.valor(valorRecebido) //
				.valorRecebido(valorRecebido) //
				.cliente(cliente) //
				.build();

		repository.save(mensalidade);
	}
}
