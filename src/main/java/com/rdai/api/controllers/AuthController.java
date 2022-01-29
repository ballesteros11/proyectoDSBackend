package com.rdai.api.controllers;


import com.rdai.api.controllers.util.AuthUtil;
import com.rdai.api.validations.JwtValidation;
import com.rdai.api.validations.LoginValidation;
import com.rdai.api.validations.MessageValidation;
import com.rdai.api.validations.UserValidation;
import com.rdai.domain.services.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping(AuthController.MAINROUTE)
public class AuthController {

    public static final String MAINROUTE = "/AUTH";
    public static final String NUEVO_USUARIO = "/NUEVO-USUARIO";
    public static final String LOGIN = "/LOGIN";
    public static final String REFRESH_TOKEN = "/REFRESH_TOKEN";

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthUtil authUtil;
    @CrossOrigin
    @PostMapping(NUEVO_USUARIO)
    public ResponseEntity<MessageValidation> nuevoUsuario(@Valid @RequestBody
                                                          UserValidation userValidation, BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new MessageValidation("Campos mal puestos"), HttpStatus.NOT_ACCEPTABLE);

        if(Boolean.TRUE.equals(usuarioService.existsByUsername(userValidation.getNombreUsuario())))
            return new ResponseEntity<>(new MessageValidation("El nombre se encuentra en uso"), HttpStatus.BAD_REQUEST);

        try{
            usuarioService.crearUsuario(authUtil.createUser(userValidation));
            return new ResponseEntity<>(new MessageValidation("Usuario creado correctamente"), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(new MessageValidation("Error desconocido. perdòn :c"), HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }
    @CrossOrigin
    @PostMapping(LOGIN)
    public ResponseEntity<JwtValidation> loginUsuario(@Valid @RequestBody
                                                      LoginValidation loginValidation, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new JwtValidation("Campos Mal Puestos"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(authUtil.autenticarUsuario(loginValidation), HttpStatus.OK);

    }
    @CrossOrigin
    @PostMapping(REFRESH_TOKEN)
    public ResponseEntity<JwtValidation> refreshToken(@Valid @RequestBody
                                                      JwtValidation jwtValidation, BindingResult bindingResult)
                                                        throws ParseException {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new JwtValidation("Sin Token"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(authUtil.refrescarToken(jwtValidation), HttpStatus.OK);
    }









}
