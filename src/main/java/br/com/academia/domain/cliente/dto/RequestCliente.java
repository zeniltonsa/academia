package br.com.academia.domain.cliente.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestCliente {

	private Long codigo;

	@NotBlank(message = "Nome completo deve ser preenchido")
	private String nomeCompleto;

	@NotNull(message = "Data de nascimento deve ser preenchida")
	private LocalDate dataNascimento;

	@NotNull(message = "Data de cadastro deve ser preenchida")
	private LocalDateTime dataCadastro;

	@NotNull(message = "Telefone deve ser preenchido")
	private String numeroTelefone;

	private boolean pagamentoRecebido;

	private BigDecimal valorRecebido;

}
