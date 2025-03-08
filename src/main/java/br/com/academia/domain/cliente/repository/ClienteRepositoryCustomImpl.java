package br.com.academia.domain.cliente.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.academia.domain.cliente.dto.filter.FiltroCliente;
import br.com.academia.domain.cliente.model.EntCliente;
import jakarta.persistence.EntityManager;

public class ClienteRepositoryCustomImpl implements ClienteRepositoryCustom {

	private final EntityManager manager;

	public ClienteRepositoryCustomImpl(EntityManager manager) {
		this.manager = manager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntCliente> filtrar(FiltroCliente filtro) {
		var query = new StringBuilder("SELECT * FROM tb_cliente WHERE 1=1");

		if (StringUtils.isNotEmpty(filtro.nomeCompleto()))
			query.append(" AND NOME_COMPLETO LIKE UPPER('%" + filtro.nomeCompleto() + "%')");

		if (filtro.dataInicio() != null && filtro.dataFinal() != null) {
			query.append(
					" AND DATE(DT_CADASTRO) BETWEEN '" + filtro.dataInicio() + "' AND '" + filtro.dataFinal() + "' ");
		}

		return manager.createNativeQuery(query.toString(), EntCliente.class).getResultList();
	}

}
