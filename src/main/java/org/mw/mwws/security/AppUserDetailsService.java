package org.mw.mwws.security;

import org.mw.mwws.entity.Role;
import org.mw.mwws.entity.User;
import org.mw.mwws.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));

		return org.springframework.security.core.userdetails.User
				.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.authorities(getAuthorities(user))
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
	}


	public Collection<? extends GrantedAuthority> getAuthorities(User user) {
		Set<GrantedAuthority> authorities = new HashSet<>();

		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoles().stream()
				.map(Role::getName)
				.findFirst()
				.orElseThrow(() ->new UsernameNotFoundException("User has no roles."))));

		user.getRoles().forEach(role -> {
			role.getPermissions().forEach(permission -> {
				authorities.add(new SimpleGrantedAuthority("PERMISSION_" + permission.getName()));
			});
		});

		return authorities;
	}

}
