package br.com.academia.domain.aluno.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.academia.domain.aluno.model.EntAluno;

public class AlunoDTO {

	private Long codigo;

	private String nomeCompleto;

	private LocalDate dataDeNascimento;

	private LocalDateTime dataDeCadastro;

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

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public LocalDateTime getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(LocalDateTime dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	public static AlunoDTO from(EntAluno entity) {
		var dto = new AlunoDTO();
		dto.setCodigo(entity.getId());
		dto.setNomeCompleto(entity.getNomeCompleto());
		dto.setDataDeNascimento(entity.getDataNascimento());
		dto.setDataDeCadastro(entity.getDataCadastro());

		return dto;
	}
}
