package br.com.academia.domain.aluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academia.domain.aluno.model.EntAluno;

public interface AlunoRepository extends JpaRepository<EntAluno, Long> {

}
