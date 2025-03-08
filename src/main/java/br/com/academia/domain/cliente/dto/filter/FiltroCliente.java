package br.com.academia.domain.cliente.dto.filter;

import java.time.LocalDate;

public record FiltroCliente(
		String nomeCompleto, 
		LocalDate dataInicio, 
		LocalDate dataFinal) {}
