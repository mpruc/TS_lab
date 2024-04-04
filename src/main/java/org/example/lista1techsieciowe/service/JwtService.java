package org.example.lista1techsieciowe.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.lista1techsieciowe.commonTypes.UserRole;
import org.example.lista1techsieciowe.entity.Login;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {
    public String generateToken(Login logDetail) {
        return generateToken(new HashMap<>(), logDetail);

    }
    public boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch(Exception e) {
            return false;
        }
    }
    @Value("${jwt.token.key}")
    private String key;


    private Date extractDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public UserRole extractRole(String token) {
        String roleString = extractClaim(token, (claims) -> claims.get("role", String.class));
        return UserRole.valueOf(roleString);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractDate(token).before(new Date());
    }
    private String generateToken(Map<String, Object>extraClaims, Login logDetails) {
        long timeMillis = System.currentTimeMillis();
        String token = Jwts.builder()
                    .issuedAt(new Date(timeMillis))
                    .expiration(new Date(timeMillis + 5*60*1000))// 5 min ważności
                    .claim("id", "userId") //z bazy danych
                    .claim("userRole", "ROLE_") // rola z bd
                    .signWith(getSigningKey())
                    .compact();
            return token;
    }
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Claims extractAllClaims(String token) {
         return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

}
