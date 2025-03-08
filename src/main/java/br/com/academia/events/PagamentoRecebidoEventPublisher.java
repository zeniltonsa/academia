package br.com.academia.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PagamentoRecebidoEventPublisher {

	private final ApplicationEventPublisher publisher;

	public PagamentoRecebidoEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	public void pagamentoRecebido(PagamentoRecebidoEvent pagamentoRecebidoEvent) {
		publisher.publishEvent(pagamentoRecebidoEvent);
	}
}
