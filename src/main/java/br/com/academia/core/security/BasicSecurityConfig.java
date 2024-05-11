package br.com.academia.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class BasicSecurityConfig {

	@Autowired
	private CustomBasicAuthenticationFilter customBasicAuthenticationFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
		return http //
				.authorizeHttpRequests(authorize -> {

					// rotas sem autorização
					authorize.requestMatchers( //
							mvc.pattern(HttpMethod.POST, "/v1/usuario"), //
							mvc.pattern(HttpMethod.GET, "/v3/api-docs/**"), //
							mvc.pattern(HttpMethod.GET, "/swagger-ui/**"), //
							mvc.pattern(HttpMethod.GET, "/actuator/**") //

					).permitAll();

					// qualquer outra rota deve conter o JWT
					authorize.anyRequest().authenticated();
				})

				// configuração de CORS e CSRF
				.cors(Customizer.withDefaults()) //
				.csrf(csrf -> csrf.disable()) //

				// filtro para validação do JWT
				.addFilterBefore(customBasicAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

				// desativa a criação de sessões no Spring Security
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.build();
	}

	@Bean
	MvcRequestMatcher.Builder builder(HandlerMappingIntrospector instrospector) {
		return new MvcRequestMatcher.Builder(instrospector);
	}

}