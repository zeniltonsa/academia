package br.com.academia.domain.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academia.domain.auth.domain.EntPermissao;

public interface PermissaoRepository extends JpaRepository<EntPermissao, Long> {

}
