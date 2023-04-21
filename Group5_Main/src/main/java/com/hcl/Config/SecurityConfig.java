package com.hcl.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hcl.JwtFilter.JwtFilter;

@SuppressWarnings("deprecation")
	@EnableWebSecurity
	public class SecurityConfig extends WebSecurityConfigurerAdapter
	{
		@Autowired
		private UserDetailsService userDetailsService;
		
		@Autowired
		private BCryptPasswordEncoder passwordEncoder;
		
		@Autowired
		private InvalidAuthenticationEntryPoint authenticationEntryPoint;
		
		@Autowired
		private JwtFilter jwtFilter;
		
		@Bean
		@Override
		protected AuthenticationManager authenticationManager() throws Exception {
			// TODO Auto-generated method stub
			return super.authenticationManager();
		}
		
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception
		{
			auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder);
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception
		{
			
			http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/login","/register").permitAll()
				.antMatchers("/OwnerLogin/**").permitAll()
				.antMatchers("/User/*").permitAll()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint((AuthenticationEntryPoint) authenticationEntryPoint)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				//TODO verify user for second requestOnwards
				.and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				;
		}
	}



