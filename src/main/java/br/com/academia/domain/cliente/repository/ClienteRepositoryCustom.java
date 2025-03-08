package br.com.academia.domain.cliente.repository;

import java.util.List;

import br.com.academia.domain.cliente.dto.filter.FiltroCliente;
import br.com.academia.domain.cliente.model.EntCliente;

public interface ClienteRepositoryCustom {

	List<EntCliente> filtrar(FiltroCliente filtro);
}
