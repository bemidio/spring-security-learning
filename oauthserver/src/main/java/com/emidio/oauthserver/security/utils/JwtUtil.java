package com.emidio.oauthserver.security.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JwtUtils
 */
@Component
public class JwtUtil {

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_ROLE = "role";
    static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}") 
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {

        try {
            Claims claims = this.getClaimsFromToken(token);
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }

    }

    public Date getExpirationDateFromToken(String token) {

        try {
            return this.getClaimsFromToken(token).getExpiration();
        } catch (Exception e) {
            return null;
        }
    }

    public String refreshToken(String token) {
        Claims claims = this.getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return this.gerarToken(claims);
    }

    public Boolean tokenValido(String token) {
        return !tokenExpirado(token);
    }

    /**
     * Retorna um novo token JWT com base nos dados do usuários.
     * 
     * @param userDetails
     * @return String
     */
    public String obterToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
        claims.put(CLAIM_KEY_CREATED, new Date());

        return gerarToken(claims);
    }

    private Date gerarDataExpiracao() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * Verifica se um token JTW está expirado.
     * 
     * @param token
     * @return boolean
     */
    private boolean tokenExpirado(String token) {
        Date dataExpiracao = this.getExpirationDateFromToken(token);
        if (dataExpiracao == null) {
            return false;
        }
        return dataExpiracao.before(new Date());
    }

    /**
     * Gera um novo token JWT contendo os dados (claims) fornecidos.
     * 
     * @param claims
     * @return String
     */
    private String gerarToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(gerarDataExpiracao())
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
}