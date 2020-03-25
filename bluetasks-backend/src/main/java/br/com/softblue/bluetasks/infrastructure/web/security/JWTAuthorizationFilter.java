package br.com.softblue.bluetasks.infrastructure.web.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String token = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
		
		if (token != null && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			UsernamePasswordAuthenticationToken authentication = getAuthentcation(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentcation(String token) {
		String username = Jwts.parser().setSigningKey(SecurityConstants.SECRET_KEY)
			.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
			.getBody().getSubject();
		
		if (username != null) {
			return new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.NO_AUTHORITIES);
		}
		
		return null;
	}
}
