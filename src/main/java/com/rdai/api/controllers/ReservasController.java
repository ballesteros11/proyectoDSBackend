package com.rdai.api.controllers;

import com.rdai.api.controllers.util.ReservaSearchUtil;
import com.rdai.api.controllers.util.ReservasUtil;
import com.rdai.api.validations.DisponibilidadValidation;
import com.rdai.api.validations.MessageValidation;
import com.rdai.configuration.jwt.JwtProvider;
import com.rdai.data.models.entities.DisponibilidadCasaEntity;
import com.rdai.domain.services.DisponibilidadCasaService;
import com.rdai.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(ReservasController.RUTAPRINCIPAL)
public class ReservasController {

    public static final String RUTAPRINCIPAL = "/api/reservas";
    public static final String BUSCARPORUSUARIO = "/busquedau";
    public static final String BUSCARPORCASA = "/busquedac/{id}";
    public static final String CREARESERVA = "/crear";

    @Autowired
    private DisponibilidadCasaService reservaService;

    @Autowired
    private ReservasUtil reservasUtil;

    @Autowired
    private ReservaSearchUtil reservaSearchUtil;

    @Autowired
    private JwtProvider jwtProvider;


    @PostMapping(CREARESERVA)
    public ResponseEntity<MessageValidation> crearReserva(@Valid @RequestBody DisponibilidadValidation reserva,
                                                          BindingResult bindingResult,
                                                          @RequestHeader("Authorization") String token) {

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new MessageValidation("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        }

        if(!reservasUtil.validarFechas(reserva)){
            return new ResponseEntity<>(new MessageValidation("La casa no puede ser reservada"), HttpStatus.NOT_ACCEPTABLE);
        }

        if(!reservasUtil.verificarDisponibilidad(reserva)){
            return new ResponseEntity<>(new MessageValidation("La casa no está disponible para esas fechas"), HttpStatus.CONFLICT);
        }


        DisponibilidadCasaEntity dispEntity = reservasUtil.crearReserva(reserva, token);

        try{
            reservaService.guardarReserva(dispEntity);
            return new ResponseEntity<>(new MessageValidation("Reserva creada correctamente"), HttpStatus.CREATED);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(new MessageValidation("Error desconocido. perdòn :c"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(BUSCARPORUSUARIO)
    public ResponseEntity<List<DisponibilidadValidation>> obtenerReservasPorUsuario(@RequestHeader("Authorization") String token){
        token = token.split(" ")[1];

        String username = jwtProvider.getUserNameFromToken(token);

        List<DisponibilidadValidation> resultado = reservaSearchUtil.obtenerReservasPorNombreUsuario(username);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping(BUSCARPORCASA)
    public ResponseEntity<List<DisponibilidadValidation>> obtenerPorCasa(@PathVariable String id){
        List<DisponibilidadValidation> resultado = reservaSearchUtil.obtenerReservasPorIdCasa(id);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }


}
