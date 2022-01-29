package com.rdai.api.controllers;

import com.rdai.api.controllers.util.UsuarioUtil;
import com.rdai.api.validations.PublicUserValidation;
import com.rdai.configuration.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(UsuarioController.RUTAPRINCIPAL)
public class UsuarioController {

    public static final String RUTAPRINCIPAL = "/api/usuariopublico";
    public static final String BUSQUEDAPORNOMBREUSUARIO = "/busqueda/{id}";
    public static final String BUSQUEDALOGGEDUSER = "/busqueda";

    @Autowired
    private UsuarioUtil usuarioUtil;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping(BUSQUEDAPORNOMBREUSUARIO)
    public ResponseEntity<PublicUserValidation> traerInfoPublica(@PathVariable String id){

        PublicUserValidation usuario = usuarioUtil.buscarInfoUsuario(id);

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping(BUSQUEDALOGGEDUSER)
    public ResponseEntity<PublicUserValidation> traerInfoPerfilPropio(@RequestHeader("Authorization")String token){
        token = token.split(" ")[1];

        String username = jwtProvider.getUserNameFromToken(token);
        PublicUserValidation usuario = usuarioUtil.buscarInfoUsuario(username);

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

}
