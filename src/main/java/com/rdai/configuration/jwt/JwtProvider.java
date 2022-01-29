package com.rdai.configuration.jwt;

import java.text.ParseException;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.rdai.data.models.MainUsuario;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import com.rdai.api.validations.JwtValidation;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private static final String ROLES = "roles";

    public String generaToken(Authentication authenticacion){
        MainUsuario mainUsuario = (MainUsuario) authenticacion.getPrincipal();
        List<String> roles= mainUsuario.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return Jwts.builder().setSubject(mainUsuario.getNombreUsuario())
                .setIssuedAt(new Date())
                .claim(ROLES, roles)
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }


    public boolean validarToken (String token) {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("unsupported token");
        } catch (ExpiredJwtException e) {
            logger.error("Token Expirado");
        } catch (IllegalArgumentException e) {
            logger.error("Token Ilegal");
        } catch (SignatureException e) {
            logger.error("Fallo en la firma");
        }
        return false;


    }

    public String refreshToken (JwtValidation jwtValidation) throws ParseException {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwtValidation.getToken());
        } catch (ExpiredJwtException e) {
            JWT jwt = JWTParser.parse(jwtValidation.getToken());
            JWTClaimsSet claims = jwt.getJWTClaimsSet();
            String nombreUsuario = claims.getSubject();
            List<String> roles = (List<String>) claims.getClaim(ROLES);

            return Jwts.builder()
                    .setSubject(nombreUsuario)
                    .claim(ROLES, roles)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + expiration))
                    .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                    .compact();
        }
        return null;
    }


    public String getUserNameFromToken (String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token)
                .getBody().getSubject();
    }






}
