package com.backendprogramming.bookstore.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/resources/**", "/signup", "/about").permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/booklist", true).permitAll().and().logout().permitAll().and()
				.httpBasic();
		return http.build();
	}
	/*
	@Bean
	public UserDetailsService userDetailsService() {
		List<UserDetails> users = new ArrayList<>();
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
				.build();

		users.add(user);

		user = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER", "ADMIN").build();

		users.add(user);
		return new InMemoryUserDetailsManager(users);
	}
	*/

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
