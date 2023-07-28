package com.amis.misa.filter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.amis.misa.entities.app.AccountEmployee;
import com.amis.misa.services.impl.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
@PropertySource("classpath:jwt.properties")
public class JwtTokenprovider {
	
	@Value("${jwt.secret}")
    private  String JWT_SECRET;
 
	@Value("${jwt.expiration}")
    private  long JWT_EXPIRATION;
    
    public String generateToken(AccountEmployee userDetails) {
    	Date now=new Date();
    	Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
    	return Jwts.builder()
    				.setSubject(userDetails.getUsername())
    				.setIssuedAt(now)
    				.setExpiration(expiryDate)
    				.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
    				.compact();
    }
    public boolean isValidToken(String authToken) {
    	try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
        	 System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
        	 System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
        	 System.out.println("JWT claims string is empty.");
        }
        return false;
    }
    public String getUserFromJWT(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(JWT_SECRET)
                            .parseClaimsJws(token)
                            .getBody();

        return (claims.getSubject());
    }
    
}
