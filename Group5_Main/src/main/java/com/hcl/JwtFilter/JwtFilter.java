package com.hcl.JwtFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hcl.JwtUtil.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter 
{

	@Autowired
	JwtUtil util;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		//1.read token from Auth header
		String token=request.getHeader("Authorization");
		if(token!=null)
		{
			//do validation
			String userName= util.getUserName(token);
			System.out.println(userName);
			System.out.println(SecurityContextHolder.getContext().getAuthentication());
			if(userName!=null && 
					SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UserDetails user=userDetailsService.loadUserByUsername(userName);
				
				//validate token
				boolean isValid=util.validateToken(token, userName);
				if(isValid)
				{
					UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(userName,user.getPassword(),user.getAuthorities());
					
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					//fnialObject
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
			
		}
		filterChain.doFilter(request, response);
		
	}

}
