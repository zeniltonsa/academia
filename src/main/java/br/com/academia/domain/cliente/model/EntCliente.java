package br.com.academia.domain.cliente.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.academia.domain.auth.domain.EntUsuario;
import br.com.academia.domain.cliente.dto.RequestCliente;
import br.com.academia.domain.financeiro.model.EntMensalidade;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_CLIENTE")
public class EntCliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2779568684746963749L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "NOME_COMPLETO", length = 100, nullable = false, unique = true)
	private String nomeCompleto;

	@NotNull
	@Column(name = "DT_NASCIMENTO", nullable = false)
	private LocalDate dataNascimento;

	@NotNull
	@Column(name = "DT_CADASTRO", nullable = false)
	private LocalDateTime dataCadastro;

	@NotNull
	@Column(name = "NR_TELEFONE", nullable = false)
	private String numeroTelefone;

	@OneToMany(mappedBy = "cliente", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<EntMensalidade> mensalidades;

	@ManyToOne
	@JoinColumn(name = "USUARIO_ID", foreignKey = @ForeignKey(name = "FK_Usuario"))
	private EntUsuario cadastradoPor;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public static EntCliente from(RequestCliente request) {
		var cliente = new EntCliente();
		cliente.setId(request.getCodigo());
		cliente.setNomeCompleto(request.getNomeCompleto().toUpperCase());
		cliente.setDataNascimento(request.getDataNascimento());
		cliente.setDataCadastro(request.getDataCadastro());
		cliente.setNumeroTelefone(limparNumeroTelefone(request.getNumeroTelefone()));

		return cliente;
	}

	private static String limparNumeroTelefone(String numeroTelefone) {
		return numeroTelefone.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
	}

}
