package com.amis.misa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;

import com.amis.misa.entities.app.AccountEmployee;
import com.amis.misa.filter.JWTAuthenticationFilter;
import com.amis.misa.services.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	CustomUserDetailsService userDetailsService;
	@Autowired
	JWTAuthenticationFilter filterAuth;
	@Bean
	JWTAuthenticationFilter jwtFilter() {
		return new JWTAuthenticationFilter();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		// Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.cors().configurationSource(request->corsConfiguration());
//		http.addFilterBefore(filterAuth, UsernamePasswordAuthenticationFilter.class);
//		http.csrf().disable().authorizeRequests() // bat dau cau hinh security
//
//				// cho phép các request static không bị ràng buộc
//
//				// các request kiểu: "/admin/" phải đăng nhập
//				
//				.antMatchers("/api/v1/Employees/filter/**").permitAll()
//				.antMatchers("/api/v1/Employees/insert")
//					.hasAnyAuthority("ADMIN")
//				.antMatchers("/api/v1/Employees/update").hasAnyAuthority("ADMIN")
//				.requestMatchers ("/api/v1/Employees/NewEmployeeCode").hasAnyAuthority("ADMIN")
//				.and()
////				.formLogin().loginProcessingUrl("/api/v1/perform_login")
////				.defaultSuccessUrl("/api/v1/Employees").and()
//				.exceptionHandling()
//					.accessDeniedPage("/403")
//				.and()
//				.authenticationProvider(authenticationProvider());
//		
//				//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		return http.build();
//		
//	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	

		http.cors().configurationSource(request->corsConfiguration());
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
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(jwtFilter(),  UsernamePasswordAuthenticationFilter.class);
				//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}

	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
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
	  CorsConfiguration corsConfiguration() {
	        CorsConfiguration corsConfiguration = new CorsConfiguration();
	        corsConfiguration.applyPermitDefaultValues();
	        corsConfiguration.addAllowedMethod(HttpMethod.PATCH);
	        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
	        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
	        corsConfiguration.addAllowedOrigin("*");
	        corsConfiguration.addAllowedHeader("X-Requested-With, X-Auth-Token, Content-Type, Content-Length, Authorization, Access-Control-Allow-Headers, Accept, Access-Control-Allow-Methods, Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
	        return corsConfiguration;
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
