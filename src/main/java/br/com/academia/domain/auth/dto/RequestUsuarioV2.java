package br.com.academia.domain.auth.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestUsuarioV2 {

	@NotBlank(message = "Nome deve ser preenchido")
	private String nome;

	@NotBlank(message = "Senha deve ser preenchida")
	private String senha;

	private List<Long> permissoes = new ArrayList<>();

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
