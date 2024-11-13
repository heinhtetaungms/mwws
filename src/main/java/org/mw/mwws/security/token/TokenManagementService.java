package org.mw.mwws.security.token;

import org.mw.mwws.api.input.TokenRefreshForm;
import org.mw.mwws.api.input.TokenRequestForm;
import org.mw.mwws.commons.dto.TokenResponse;
import org.mw.mwws.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenManagementService {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenParser jwtTokenParser;
	
	@Autowired
	private JwtTokenGenerator jwtTokenGenerator;
	
	@Autowired
	private UserRepo userRepo;
	
	@Transactional(readOnly = true)
	public TokenResponse generate(TokenRequestForm form) {
		
		var usernamePasswordToken = UsernamePasswordAuthenticationToken.unauthenticated(form.username(), form.password());
		var authentication = authenticationManager.authenticate(usernamePasswordToken);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return getResponse(authentication);
	}

	@Transactional(readOnly = true)
	public TokenResponse refresh(TokenRefreshForm form) {
		var authentication = jwtTokenParser.parse(TokenType.Refresh, form.refreshToken());
		return getResponse(authentication);
	}


	private TokenResponse getResponse(Authentication authentication) {
		
		var account = userRepo.findByUsername(authentication.getName())
				.orElseThrow(() -> new UsernameNotFoundException("Invalid login id."));

		var roles = authentication.getAuthorities().stream().map(a -> a.getAuthority()).toList();
		System.out.println("roles => " + roles);
		var accessToken = jwtTokenGenerator.generate(TokenType.Access, authentication);
		var refreshToken = jwtTokenGenerator.generate(TokenType.Refresh, authentication);
		
		return TokenResponse.from(account, accessToken, refreshToken);
	}
}
