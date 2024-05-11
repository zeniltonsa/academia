package br.com.academia.core.security;

import java.io.IOException;
import java.util.Base64;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.academia.domain.auth.domain.EntUsuario;
import br.com.academia.domain.auth.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomBasicAuthenticationFilter extends OncePerRequestFilter {

	private static final String AUTHORIZATION = "Authorization";
	private static final String BASIC = "Basic ";

	private final UsuarioRepository usuarioRepository;

	private final PasswordEncoder passwordEncoder;

	public CustomBasicAuthenticationFilter(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (isBasicAuthentication(request)) {
			String[] credentials = decodeBase64(getHeader(request).replace(BASIC, "")).split(":");

			String username = credentials[0];
			String password = credentials[1];

			EntUsuario usuario = usuarioRepository.findByLoginFetchPermissoes(username);

			if (usuario == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Acesso não autorizado: usuário não existe.");
				return;
			}

			boolean valid = checkPassword(usuario.getSenha(), password);

			if (!valid) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Acesso não autorizado: senha está incorreta.");
				return;
			}

			setAuthentication(usuario);
		}

		filterChain.doFilter(request, response);
	}

	private void setAuthentication(EntUsuario user) {
		Authentication authentication = createAuthenticationToken(user);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private Authentication createAuthenticationToken(EntUsuario user) {
		UsuarioPrincipal userPrincipal = UsuarioPrincipal.criar(user);
		return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
	}

	private boolean checkPassword(String userPassword, String loginPassword) {
		return passwordEncoder.matches(loginPassword, userPassword);
	}

	private String decodeBase64(String base64) {
		byte[] decodeBytes = Base64.getDecoder().decode(base64);
		return new String(decodeBytes);
	}

	private boolean isBasicAuthentication(HttpServletRequest request) {
		String header = getHeader(request);
		return header != null && header.startsWith(BASIC);
	}

	private String getHeader(HttpServletRequest request) {
		return request.getHeader(AUTHORIZATION);
	}
}
