package br.com.academia.domain.aluno.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.academia.domain.aluno.dto.RequestAluno;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_ALUNO")
public class EntAluno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2779568684746963749L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	private int version;

	@NotNull
	@Column(name = "NOME_COMPLETO", length = 100, nullable = false, unique = true)
	private String nomeCompleto;

	@NotNull
	@Column(name = "DT_NASCIMENTO", nullable = false)
	private LocalDate dataNascimento;

	@NotNull
	@Column(name = "DT_CADASTRO", nullable = false)
	private LocalDateTime dataCadastro;

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

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public static EntAluno from(RequestAluno request) {
		var aluno = new EntAluno();
		aluno.setId(request.getCodigo());
		aluno.setNomeCompleto(request.getNomeCompleto());
		aluno.setDataNascimento(request.getDataDeNascimento());
		aluno.setDataCadastro(request.getDataDeCadastro());

		return aluno;
	}

}
