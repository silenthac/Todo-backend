package com.example.todo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;



@Component
public class JwtUtil {
	
	private final String  SECRET  = "secret-key";
	private final long  EXPIRATION_TIME = 86400000;
	
	
	public String  generateToken(String UserName)
	{
		
		return Jwts.builder()
				.setSubject(UserName)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
	}
	
	
	public String  extractUserName(String token)
	{
		
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
		
		
	}
	
	public boolean validateToken(String token)
	{
		
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		}
		
		catch(JwtException | IllegalArgumentException e)
		{
			return false;
		}
		
		
		
	}
	
}
