package br.com.academia.domain.financeiro;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

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

	@NotNull(message = "Código do aluno deve ser preenchido")
	private Long codigoAluno;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public LocalDate getDataDeVencimento() {
		return dataDeVencimento;
	}

	public void setDataDeVencimento(LocalDate dataDeVencimento) {
		this.dataDeVencimento = dataDeVencimento;
	}

	public LocalDate getDataDePagamento() {
		return dataDePagamento;
	}

	public void setDataDePagamento(LocalDate dataDePagamento) {
		this.dataDePagamento = dataDePagamento;
	}

	public Long getCodigoAluno() {
		return codigoAluno;
	}

	void setCodigoAluno(Long codigoAluno) {
		this.codigoAluno = codigoAluno;
	}

}
