package com.rdai.api.controllers.util;

import com.rdai.api.validations.PublicUserValidation;
import com.rdai.data.models.entities.UsuarioEntity;
import com.rdai.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioUtil {

    @Autowired
    private UsuarioService usuarioService;

    public PublicUserValidation buscarInfoUsuario(String nombreUsuario){

        UsuarioEntity user = usuarioService.getByUsername(nombreUsuario).orElseGet(null);

        return PublicUserValidation.builder().nombreCompleto(user.getNombreCompleto()).nombreUsuario(user.getNombreUsuario())
                .pais(user.getPais()).build();

    }




}
