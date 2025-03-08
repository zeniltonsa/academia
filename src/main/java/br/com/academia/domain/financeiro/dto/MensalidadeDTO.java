package br.com.academia.domain.financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.academia.domain.cliente.dto.response.ClienteDTO;
import br.com.academia.domain.financeiro.model.EntMensalidade;
import lombok.Data;

@Data
public class MensalidadeDTO {

	private Long codigo;

	private BigDecimal valor;

	private BigDecimal valorRecebido;

	private LocalDate dataDeVencimento;

	private LocalDate dataDePagamento;

	private ClienteDTO cliente;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public static MensalidadeDTO from(EntMensalidade entity) {
		var dto = new MensalidadeDTO();
		dto.setCodigo(entity.getId());
		dto.setValor(entity.getValor());
		dto.setValorRecebido(entity.getValorRecebido());
		dto.setDataDeVencimento(entity.getDataVencimento());
		dto.setDataDePagamento(entity.getDataPagamento());

		return dto;
	}
}
