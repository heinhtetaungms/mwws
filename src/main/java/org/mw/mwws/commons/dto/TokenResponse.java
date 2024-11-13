package org.mw.mwws.commons.dto;

import org.mw.mwws.entity.User;

public record TokenResponse(
		String username,
		String accessToken,
		String refreshToken) {

	public static TokenResponse from(User user, String accessToken, String refreshToken) {
		return new TokenResponse(user.getUsername(),
				accessToken,
				refreshToken);
	}

}
