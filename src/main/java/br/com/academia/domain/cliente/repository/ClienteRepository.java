package br.com.academia.domain.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academia.domain.cliente.model.EntCliente;

public interface ClienteRepository extends JpaRepository<EntCliente, Long>, ClienteRepositoryCustom {

}
