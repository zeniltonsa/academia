package br.com.academia.domain.cliente.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
public class ClienteDTO {

	private Long id;

	private String nomeCompleto;

	private LocalDate dataNascimento;

	private LocalDateTime dataCadastro;

	private String numeroTelefone;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
