package com.amis.misa.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.amis.misa.services.impl.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	CustomUserDetailsService userDetailsService;
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
	        return new BCryptPasswordEncoder();
	    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests() // bat dau cau hinh security

				// cho phép các request static không bị ràng buộc


				// các request kiểu: "/admin/" phải đăng nhập

				.antMatchers("/api/v1/Employees/**").permitAll()
				
				.and()

				// cấu hình trang đăng nhập
				//.formLogin().loginPage("/perfume-shop/login.html").loginProcessingUrl("/perform_login")
				//.defaultSuccessUrl("/perfume-shop/login-success", true)
				//.failureUrl("/perfume-shop/login.html?login_error=true").permitAll()
				.formLogin()
					.defaultSuccessUrl("/api/v1/Employees",true)
					.permitAll();
		
				//.and()

				// cấu hình cho phần logout
				//.logout().logoutUrl("/perfume-shop/logout.html").logoutSuccessUrl("/perfume-shop/login.html")
				//.invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();
	}
	   @Override
	    protected void configure(AuthenticationManagerBuilder auth)
	            throws Exception {
	        auth.userDetailsService(userDetailsService) // Cung cáp userservice cho spring security
	            .passwordEncoder(passwordEncoder()); // cung cấp password encoder
	    }
	   public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
}
