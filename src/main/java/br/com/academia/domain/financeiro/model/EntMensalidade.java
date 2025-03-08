package br.com.academia.domain.financeiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.academia.domain.cliente.model.EntCliente;
import br.com.academia.domain.financeiro.RequestMensalidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
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

	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID", nullable = false)
	private EntCliente cliente;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public static EntMensalidade from(RequestMensalidade request, EntCliente cliente) {
		return EntMensalidade.builder() //
				.id(request.getCodigo()) //
				.valor(request.getValor()) //
				.valorRecebido(request.getValorRecebido()) //
				.dataPagamento(request.getDataDePagamento()) //
				.dataVencimento(request.getDataDePagamento()) //
				.cliente(cliente) //
				.build();
	}
}
