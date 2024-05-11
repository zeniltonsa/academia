package br.com.academia.domain.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.academia.domain.auth.domain.EntUsuario;

public interface UsuarioRepository extends JpaRepository<EntUsuario, Long> {

	EntUsuario findByNome(String usuario);

	@Query("SELECT u FROM EntUsuario u JOIN FETCH u.permissoes WHERE u.nome = :nomeUsuario")
	EntUsuario findByNomeFetchPermissoes(@Param("nomeUsuario") String nomeUsuario);

}
