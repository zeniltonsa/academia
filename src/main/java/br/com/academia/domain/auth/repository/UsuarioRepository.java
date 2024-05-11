package br.com.academia.domain.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.academia.domain.auth.domain.EntUsuario;

public interface UsuarioRepository extends JpaRepository<EntUsuario, Long> {

	EntUsuario findByNome(String usuario);

	EntUsuario findByLogin(String login);

	@Query("SELECT u FROM EntUsuario u JOIN FETCH u.permissoes WHERE u.login = :login")
	EntUsuario findByLoginFetchPermissoes(@Param("login") String login);

}
