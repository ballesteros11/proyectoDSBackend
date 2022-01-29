package com.rdai.api.controllers;

import com.rdai.api.controllers.util.CalificacionUtil;
import com.rdai.api.validations.CalificacionValidation;
import com.rdai.api.validations.MessageValidation;
import com.rdai.data.models.entities.CalificacionEntity;
import com.rdai.domain.services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(CalificacionController.RUTAPRINCIPAL)
public class CalificacionController {


    public static final String RUTAPRINCIPAL = "/api/calificaciones";
    public static final String BUSCARPORIDRESERVA = "/busquedaid/{id}";
    public static final String CREARCALIFICACION = "/crear";
    public static final String BUSCARPORIDCASA = "/busquedaidcasa/{id}";
    public static final String BUSCARPORNOMBREUSUARIO = "/busquedanombreusuario/{id}";

    @Autowired
    private CalificacionService calificacionService;

    @Autowired
    private CalificacionUtil calificacionUtil;


    @PostMapping(CREARCALIFICACION)
    public ResponseEntity<MessageValidation> crearCalificacion(@Valid @RequestBody CalificacionValidation calificacionValidation,
                                                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<MessageValidation>(new MessageValidation("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        System.out.println(calificacionValidation.getIdCalificacion());

        CalificacionEntity calificacion = calificacionUtil.crearCalificacion(calificacionValidation);

        calificacionService.guardarCalificacion(calificacion);

        return new ResponseEntity<MessageValidation>(new MessageValidation("Calificacion creada correctamente"), HttpStatus.OK);

    }

    @GetMapping(BUSCARPORIDRESERVA)
    public ResponseEntity<List<CalificacionValidation>> buscarPorIdReserva(@PathVariable String id){

        List<CalificacionValidation> calificacion = calificacionUtil.buscarPorIdReserva(id);

        return new ResponseEntity<>(calificacion, HttpStatus.OK);

    }

    @GetMapping(BUSCARPORIDCASA)
    public ResponseEntity<List<CalificacionValidation>> buscarPorIdCasa(@PathVariable String id){

        List<CalificacionValidation> calificacion = calificacionUtil.buscarPorIdCasa(id);

        return new ResponseEntity<>(calificacion, HttpStatus.OK);

    }

    @GetMapping(BUSCARPORNOMBREUSUARIO)
    public ResponseEntity<List<CalificacionValidation>> buscarPorNombreUsuario(@PathVariable String id){

        List<CalificacionValidation> calificacion = calificacionUtil.buscarPorNombreUsuario(id);

        return new ResponseEntity<>(calificacion, HttpStatus.OK);

    }

}
