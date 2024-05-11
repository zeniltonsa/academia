package br.com.academia.core;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Bean
	OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl("http://localhost:8080");
		devServer.setDescription("Server URL in Development environment");

		Server prodServer = new Server();
		prodServer.setUrl("https://academia-4q4d.onrender.com");
		prodServer.setDescription("Server URL in Production environment");

		Contact contact = new Contact();
		contact.setEmail("zeniltondev@gmail.com");
		contact.setName("Zenilton Sá");
		contact.setUrl("https://github.com/zeniltonsa");

		License mitLicense = new License() //
				.name("MIT License") //
				.url("https://choosealicense.com/licenses/mit/");

		Info info = new Info() //
				.title("Academia API") //
				.version("1.0") //
				.contact(contact) //
				.description("Esta API expõe endpoints to gerenciar uma academia.") //
				.license(mitLicense);

		return new OpenAPI() //
				.info(info) //
				.servers(List.of(devServer, prodServer));
	}
}