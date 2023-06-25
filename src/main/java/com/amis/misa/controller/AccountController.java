package com.amis.misa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amis.misa.entities.AccountEmployee;
import com.amis.misa.filter.JwtTokenprovider;
import com.amis.misa.repositories.AccountEmployeeRepository;
import com.amis.misa.services.impl.CustomUserDetailsService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Account")
public class AccountController {

	@Autowired
	private AuthenticationManager auth;
	@Autowired
	JwtTokenprovider jwt;
	@Autowired
	CustomUserDetailsService customUser;
	@PostMapping("/perform_login")
	public ResponseEntity<?> login(@RequestBody AccountEmployee account) {
		try {
			
			Authentication authen= auth.authenticate(new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authen);
			UserDetails user= customUser.loadUserByUsername(account.getUsername());
			String jwtToken=jwt.generateToken((AccountEmployee) user);
			return ResponseEntity.ok().body(jwtToken);

		}catch (Exception e) {
//			// TODO: handle exception
			return ResponseEntity.badRequest().body(e);
	}
	}
}
