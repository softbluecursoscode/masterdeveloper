package br.com.softblue.bluefood.infrastructure.web.security;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public enum Role {
	CLIENTE, RESTAURANTE;
	
	public static Role fromAuthentication(Authentication authentication) {
		var authorities = authentication.getAuthorities();
		
		if (authorities.size() == 0) {
			return null;
		}
		
		if (authorities.size() > 1) {
			throw new IllegalStateException("Existe mais de um role");
		}
		
		String role = new ArrayList<GrantedAuthority>(authorities).get(0).getAuthority();
		return Role.valueOf(role);
	}
}
