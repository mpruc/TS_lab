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

    /**
     * Generates a JWT token for the provided login details.
     *
     * @param logDetail The login details.
     * @return The generated JWT token.
     */
    public String generateToken(Login logDetail) {
        return generateToken(new HashMap<>(), logDetail);
    }

    @Value("${jwt.token.key}")
    private String key;

    /**
     * Checks if a token is valid.
     *
     * @param token The JWT token to validate.
     * @return True if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extracts the expiration date from a JWT token.
     *
     * @param token The JWT token.
     * @return The expiration date.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token.
     * @return The extracted username.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the user role from a JWT token.
     *
     * @param token The JWT token.
     * @return The extracted user role.
     */
    public UserRole extractRole(String token) {
        String roleString = extractClaim(token, (claims) -> claims.get("role", String.class));
        return UserRole.valueOf(roleString);
    }

    /**
     * Extracts a specific claim from a JWT token using the provided claims resolver function.
     *
     * @param <T>            The type of the claim.
     * @param token          The JWT token.
     * @param claimsResolver The function to resolve the claim.
     * @return The extracted claim.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Checks if a JWT token is expired.
     *
     * @param token The JWT token.
     * @return True if the token is expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Generates a JWT token with the provided extra claims and login details.
     *
     * @param extraClaims  The extra claims to include in the token.
     * @param logDetails   The login details.
     * @return The generated JWT token.
     */
    private String generateToken(Map<String, Object> extraClaims, Login logDetails) {
        extraClaims.put("role", logDetails.getRole());
        String token = Jwts.builder()
                .claims(extraClaims)
                .subject(logDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .signWith(getSigningKey())
                .compact();
        return token;
    }

    /**
     * Retrieves the signing key used for JWT token generation.
     *
     * @return The signing key.
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Extracts all claims from a JWT token.
     *
     * @param token The JWT token.
     * @return The extracted claims.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
