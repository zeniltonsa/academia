package br.com.academia.events;

import java.math.BigDecimal;

import org.springframework.context.ApplicationEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PagamentoRecebidoEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal valorRecebido;

	public PagamentoRecebidoEvent(Object source, BigDecimal valorRecebido) {
		super(source);
		this.valorRecebido = valorRecebido;
	}

}
