package com.jorge.lab4.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationInMs;  // 1 hora de validez

    // Generar la clave secreta como un objeto Key utilizando Keys.hmacShaKeyFor()
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());  // Generar la clave de manera segura
    }

    // Generar el token JWT
    public String createToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtExpirationInMs);

        // Usar el método recomendado para firmar el JWT
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .claim("roles", principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)  // Extraer los nombres de los roles
                        .collect(Collectors.toList()))
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)  // Usar el método recomendado
                .compact();
    }

    // Extraer el token JWT del encabezado Authorization
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // Validar el token JWT
    public boolean validateToken(String token) {
        try {
            // Validación del token usando la nueva forma recomendada
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())  // Usar la clave generada
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Obtener la autenticación a partir del token
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // Usar la clave generada
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        List<String> roles = (List<String>) claims.get("roles");

        // Mapear los roles a SimpleGrantedAuthority
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                username, null, authorities);
    }

}

