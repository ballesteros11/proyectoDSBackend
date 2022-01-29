package com.rdai.api.controllers.util;

import com.rdai.api.validations.JwtValidation;
import com.rdai.api.validations.LoginValidation;
import com.rdai.api.validations.UserValidation;
import com.rdai.configuration.jwt.JwtProvider;
import com.rdai.data.models.entities.RolEntity;
import com.rdai.data.models.entities.UsuarioEntity;
import com.rdai.data.models.enums.NombreRol;
import com.rdai.domain.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthUtil {
    @Autowired
    private PasswordEncoder codificador;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private RolService rolService;

    public UsuarioEntity createUser(UserValidation userValidation){
        Set<RolEntity> userRoles = new HashSet<>();
        rolService.getByNombreRol(NombreRol.VIAJERO).ifPresent(userRoles::add);

        if(userValidation.getRoles().contains("anfitrion"))
            rolService.getByNombreRol(NombreRol.ANFITRION).ifPresent(userRoles::add);

        return UsuarioEntity.builder().id(null).nombreUsuario(userValidation.getNombreUsuario())
                .password(codificador.encode(userValidation.getPassword()))
                .pais(userValidation.getPais())
                .ciudad(userValidation.getCiudad())
                .nombreCompleto(userValidation.getNombreCompleto()).rolEntity(userRoles).build();
    }

    public JwtValidation autenticarUsuario(LoginValidation loginValidation){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginValidation.getNombreUsuario(), loginValidation.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new JwtValidation(jwtProvider.generaToken(authentication));

    }

    public JwtValidation refrescarToken(JwtValidation jwtValidation) throws ParseException {
        return new JwtValidation(jwtProvider.refreshToken(jwtValidation));
    }




}
