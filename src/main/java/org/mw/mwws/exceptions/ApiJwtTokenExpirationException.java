package org.mw.mwws.exceptions;

import org.springframework.security.core.AuthenticationException;

public class ApiJwtTokenExpirationException extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	
	public ApiJwtTokenExpirationException(String msg) {
		super(msg);
	}

}
