package br.com.academia.domain.financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.academia.domain.aluno.dto.response.AlunoDTO;
import br.com.academia.domain.financeiro.model.EntMensalidade;

public class MensalidadeDTO {

	private Long codigo;

	private BigDecimal valor;

	private BigDecimal valorRecebido;

	private LocalDate dataDeVencimento;

	private LocalDate dataDePagamento;

	private AlunoDTO aluno;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

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

	public AlunoDTO getAluno() {
		return aluno;
	}

	public void setAluno(AlunoDTO aluno) {
		this.aluno = aluno;
	}

	public static MensalidadeDTO from(EntMensalidade entity) {
		var dto = new MensalidadeDTO();
		dto.setCodigo(entity.getId());
		dto.setValor(entity.getValor());
		dto.setValorRecebido(entity.getValorRecebido());
		dto.setDataDeVencimento(entity.getDataVencimento());
		dto.setDataDePagamento(entity.getDataPagamento());
		dto.setAluno(AlunoDTO.from(entity.getAluno()));

		return dto;
	}
}
