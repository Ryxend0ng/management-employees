package com.amis.misa.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.amis.misa.services.impl.CustomUserDetailsService;
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	CustomUserDetailsService cusSer;
	@Autowired
	JwtTokenprovider provider;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String authHeader=request.getHeader("Authorization");
		final String username;
		final String jwtToken;
		if(authHeader == null ) {
			filterChain.doFilter(request, response);
			return;
		}else if(authHeader.startsWith("Bearer")) {
			
			jwtToken=authHeader.substring(7);
			username=provider.getUserFromJWT(jwtToken);
			if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails user=cusSer.loadUserByUsername(username);
				final boolean  isTokenValid = provider.isValidToken(jwtToken);
				if(isTokenValid) {
					UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
}
