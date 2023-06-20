package com.amis.misa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.amis.misa.entities.AccountEmployee;
import com.amis.misa.services.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		// Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	

		http.cors().configure(http);
		http.csrf().disable().authorizeRequests() // bat dau cau hinh security

				// cho phép các request static không bị ràng buộc

				// các request kiểu: "/admin/" phải đăng nhập

				.antMatchers("/api/v1/Employees/filter/**").permitAll()
				.antMatchers("/api/v1/Employees/insert")
					.hasAnyAuthority("ADMIN")
				.antMatchers("/api/v1/Employees/update").hasAnyAuthority("ADMIN")
				.antMatchers("/api/v1/Employees/NewEmployeeCode").hasAnyAuthority("ADMIN")
				.and()
//				.formLogin().loginProcessingUrl("/api/v1/perform_login")
//				.defaultSuccessUrl("/api/v1/Employees").and()
				.exceptionHandling()
					.accessDeniedPage("/403")
				.and();
				//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());

	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

}
