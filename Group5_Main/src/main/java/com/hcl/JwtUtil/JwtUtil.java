package com.hcl.JwtUtil;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil
{
	@Value("${jwt.secretKey}")
	private String secretKey;
	
	//6.validate user name in token and database ,expdate
	public boolean validateToken(String token,String username)
	{
		String tokenUsername=getUserName(token);
		return (username.equals(tokenUsername) && !isTokenExp(token));
	}
	
	//5.validate ExpDate
	public boolean isTokenExp(String token)
	{
		Date expDate = getExpDate(token);
		return expDate.before(new Date(System.currentTimeMillis()));
	}
	
	//4.Read subject/userName
	public String getUserName(String token)
	{
		return getClaims(token).getSubject();
	}
	
	//3.get Expiry date
	public Date getExpDate(String token)
	{
		return getClaims(token).getExpiration();
	}
	//2.Read claims
	public Claims getClaims(String token)
	{
		return Jwts.parser().setSigningKey(secretKey.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
	
	//1.generate token
	public String generateToken(String subject)
	{
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer("Sath")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
				.signWith(SignatureAlgorithm.HS512,secretKey.getBytes())
				.compact();
	}
}
