package com.project.fitwith.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

//helper class
@Component
public class JwtUtils {

    private String jwtSecret = "MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MDE=";
            private int jwtExpirationMs = 172800000;

    public String getJwtFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null  && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);
        return null;
    }

    public String generateToken(String email, String role){

        return Jwts.builder()
                .subject(email)
                .claim("roles", List.of(role))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    public boolean validateJwtToken(String JwtToken){
        try {
            Jwts.parser().verifyWith((SecretKey) key()).build()
                    .parseSignedClaims(JwtToken);

            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    private SecretKey key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getEmailFromToken(String jwt){
        return Jwts.parser().verifyWith((SecretKey) key())
                        .build().parseSignedClaims(jwt)
                        .getPayload().getSubject();
    }

    public Claims getAllClaims (String Jwt){
        return Jwts.parser().
        verifyWith((SecretKey)key()).build().parseSignedClaims(Jwt).getPayload();
    }
}
