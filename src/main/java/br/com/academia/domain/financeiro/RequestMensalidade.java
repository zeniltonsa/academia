package br.com.academia.domain.financeiro;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestMensalidade {

	private Long codigo;

	@NotNull(message = "Valor da mensalidade deve ser preenchido")
	@PositiveOrZero
	private BigDecimal valor;

	@NotNull(message = "Valor recebido da mensalidade deve ser preenchido")
	@PositiveOrZero
	private BigDecimal valorRecebido;

	@NotNull(message = "Data de vencimento deve ser preenchida")
	private LocalDate dataDeVencimento;

	private LocalDate dataDePagamento;

	@NotNull(message = "Código do cliente deve ser preenchido")
	private Long codigoCliente;

}
