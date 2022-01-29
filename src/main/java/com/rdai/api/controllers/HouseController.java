package com.rdai.api.controllers;


import com.rdai.api.controllers.util.HouseCreation;
import com.rdai.api.validations.CasaValidation;
import com.rdai.api.validations.MessageValidation;
import com.rdai.data.models.entities.CasaEntity;
import com.rdai.domain.services.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(HouseController.CASAS)
public class HouseController {

    public static final String CASAS = "/api/casas";
    public static final String SAVE = "/guardarcasa";

    @Autowired
    private CasaService casaService;

    @Autowired
    private HouseCreation houseCreation;

    @CrossOrigin
    @PostMapping(SAVE)
    public ResponseEntity<MessageValidation> guardarCasa(@Valid @RequestBody CasaValidation casaValidation,
                                                         BindingResult bindingResult,
                                                         @RequestHeader("Authorization") String token){
        token = token.split(" ")[1];

        if(bindingResult.hasErrors()){
            return new ResponseEntity<MessageValidation>(new MessageValidation("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        }

        CasaEntity casa = houseCreation.crearCasa(casaValidation, token);

        casaService.publicarCasa(casa);

        return new ResponseEntity<MessageValidation>(new MessageValidation("Casa creada correctamente"), HttpStatus.OK);

    }


}
