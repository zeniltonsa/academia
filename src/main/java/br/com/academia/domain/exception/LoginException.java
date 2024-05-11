package br.com.academia.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginException extends RuntimeException {

	private static final long serialVersionUID = 2395450004069670842L;

	public LoginException(String msg) {
		super(msg);
	}

	public LoginException(String msg, Throwable e) {
		super(msg, e);
	}

}
