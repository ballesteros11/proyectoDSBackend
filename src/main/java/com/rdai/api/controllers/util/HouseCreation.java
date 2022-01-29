package com.rdai.api.controllers.util;


import com.rdai.api.validations.CasaValidation;
import com.rdai.configuration.jwt.JwtProvider;
import com.rdai.data.models.entities.CasaEntity;
import com.rdai.data.models.entities.UsuarioEntity;
import com.rdai.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseCreation {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtProvider jwtProvider;

    public UsuarioEntity buscarUser(String username){
        return usuarioService.getByUsername(username).orElse(null);
    }

    public CasaEntity crearCasa(CasaValidation casaValidation, String token){
        String username = jwtProvider.getUserNameFromToken(token);
        UsuarioEntity usuario = buscarUser(username);

        return CasaEntity.builder().idCasa(null).direccion(casaValidation.getDireccion()).pais(casaValidation.getPais())
                .estado(casaValidation.getEstado()).ciudad(casaValidation.getCiudad()).telefono(casaValidation.getTelefono())
                .foto(casaValidation.getFoto()).usuarioEntity(usuario).build();
    }


}
