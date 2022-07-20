package com.dh.clinica.login;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String KEY = "pla4tziwii";

    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    //Valida si el usuario de la petición es igual al del token y verificamos que el token no esté vencido.
    public Boolean validateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    //Si la fecha es anterior a la actual, el token está vencido
    public Boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    //Verifica si la firma es correcta y lo devuelve
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJwt(token).getBody();
    }
}
