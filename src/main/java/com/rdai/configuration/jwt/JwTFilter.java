package com.rdai.configuration.jwt;

import com.rdai.domain.services.UsuarioDetalleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwTFilter extends OncePerRequestFilter {

    private static final Logger loggerFilter = LoggerFactory.getLogger(JwTFilter.class);

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UsuarioDetalleService usuarioDetalleService;

    private static final String BEARER = "Bearer";
    private static final String AUTORIZACION = "Authorization";


    private String getToken (HttpServletRequest request) {
        String header = request.getHeader(AUTORIZACION);

        if (header != null && header.startsWith(BEARER))
            return header.replace(BEARER, "");
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain filterChain) throws ServletException, IOException{
        try{
            String token = getToken(req);

            if(token != null && jwtProvider.validarToken(token)){
                String username = jwtProvider.getUserNameFromToken(token);
                UserDetails userDetails = usuarioDetalleService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken upAuth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(upAuth);
            }
        }catch (Exception e){
            loggerFilter.error("Fallo en el metodo Dofilter");
        }

        filterChain.doFilter(req, res);
    }


}


