package br.com.academia.domain.financeiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.academia.domain.aluno.model.EntAluno;
import br.com.academia.domain.financeiro.RequestMensalidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_MENSALIDADE")
public class EntMensalidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7624315195619314297L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	private int version;

	@NotNull
	@Column(name = "VALOR", nullable = false)
	private BigDecimal valor;

	@NotNull
	@Column(name = "VL_RECEBIDO", nullable = false)
	private BigDecimal valorRecebido;

	@NotNull
	@Column(name = "DT_VENCIMENTO", nullable = false)
	private LocalDate dataVencimento;

	@Column(name = "DT_PAGAMENTO")
	private LocalDate dataPagamento;

	@JoinColumn(name = "ALUNO_ID", nullable = false)
	private EntAluno aluno;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public EntAluno getAluno() {
		return aluno;
	}

	public void setAluno(EntAluno aluno) {
		this.aluno = aluno;
	}

	public static EntMensalidade from(RequestMensalidade request, EntAluno aluno) {
		var mensalidade = new EntMensalidade();
		mensalidade.setId(request.getCodigo());
		mensalidade.setValor(null);
		mensalidade.setValorRecebido(null);
		mensalidade.setDataPagamento(null);
		mensalidade.setDataVencimento(null);
		mensalidade.setAluno(aluno);

		return mensalidade;
	}
}
