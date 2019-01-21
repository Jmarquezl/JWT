package com.jwt.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

public class JwtUtil {
    static void addAuthentication(HttpServletResponse res, String username) {
        String token = Jwts.builder().setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + 600_000))
            .signWith(SignatureAlgorithm.HS512, "vectordelafirma")
            .compact();

        res.addHeader("Authorization", "Bearer " + token);
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey("vectordelafirma")
                    .parseClaimsJws(token.replace("Bearer", "")) //este metodo es el que valida
                    .getBody()
                    .getSubject();

            // Recordamos que para las demás peticiones que no sean /login
            // no requerimos una autenticacion por username/password
            // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
        }
        return null;
    }
}