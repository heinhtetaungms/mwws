package org.mw.mwws.api;

import org.mw.mwws.api.input.TokenRefreshForm;
import org.mw.mwws.api.input.TokenRequestForm;
import org.mw.mwws.commons.dto.TokenResponse;
import org.mw.mwws.security.token.TokenManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("token")
public class TokenManagementApi {
	
	@Autowired
	private TokenManagementService service;

	@PostMapping("generate")
	public TokenResponse generate(@Validated TokenRequestForm form, BindingResult result) {
		return service.generate(form);
	}

	@PostMapping("refresh")
	public TokenResponse refresh(@Validated TokenRefreshForm form, BindingResult result) {
		return service.refresh(form);
	}
}
