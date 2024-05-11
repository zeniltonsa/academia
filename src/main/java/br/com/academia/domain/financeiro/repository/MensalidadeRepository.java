package br.com.academia.domain.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academia.domain.financeiro.model.EntMensalidade;

public interface MensalidadeRepository extends JpaRepository<EntMensalidade, Long> {

}
