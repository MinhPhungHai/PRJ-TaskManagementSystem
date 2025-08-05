package com.minhphung.taskmanagementsystem.auth.security.jwt;

import com.minhphung.taskmanagementsystem.auth.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.jwtSecretKey}")
    private String jwtSecretKey;

    @Value("${jwt.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    /**
     * Decode Base64 jwtSecretKey -> HMAC-SHA256 signing key
     */
    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
    }

    /**
     * Extract the username from the JWT token.
     * The username is stored in the "sub" (subject) claim during token generation.
     */
    public String getUsernameFromJwtToken(String token){
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    /**
     * Extract the username from the JWT token.
     * The username is stored in the "sub" (subject) claim during token generation.
     */
    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parserBuilder().setSigningKey(key()).build()
                    .parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    /**
     * Generate a new JWT token after a user successfully logs in.
     * The token includes:
     * - subject ("sub"): the username
     * - issuedAt ("iat"): the issue timestamp
     * - expiration ("exp"): the expiration time
     * - signed with HMAC SHA-256
     */
    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationInMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
}
