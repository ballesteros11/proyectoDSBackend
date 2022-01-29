package com.rdai.api.controllers.util;


import com.rdai.api.validations.CalificacionValidation;
import com.rdai.data.models.entities.CalificacionEntity;
import com.rdai.data.models.entities.DisponibilidadCasaEntity;
import com.rdai.domain.services.CalificacionService;
import com.rdai.domain.services.DisponibilidadCasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalificacionUtil {

    @Autowired
    private DisponibilidadCasaService dispService;

    @Autowired
    private CalificacionService calificacionService;

    public CalificacionEntity crearCalificacion(CalificacionValidation calificacionValidation){

        Integer idCalificacion = null;

        DisponibilidadCasaEntity reservaAsociada = dispService.findByID(calificacionValidation.getIdDisp()).orElseGet(null);
        System.out.println(calificacionValidation.getIdCalificacion());
        if(calificacionValidation.getIdCalificacion() != null){
            idCalificacion = Integer.parseInt(calificacionValidation.getIdCalificacion());
        }
        return CalificacionEntity.builder()
                .idCalificacion(idCalificacion)
                .comentarioEstadia(calificacionValidation.getComentarioEstadia())
                .puntajeEstadia(calificacionValidation.getPuntajeEstadia())
                .comentarioViajero(calificacionValidation.getComentarioViajero())
                .puntajeViajero(calificacionValidation.getPuntajeViajero())
                .disponibilidadCasaEntity(reservaAsociada).build();
    }

    public List<CalificacionValidation> buscarPorIdReserva(String idReserva){

        return calificacionService.buscarPorIdReserva(Integer.parseInt(idReserva)).stream().map(data ->
                CalificacionValidation.builder().idCalificacion(data.getIdCalificacion().toString())
                        .idDisp(data.getDisponibilidadCasaEntity().getIdDisp().toString())
                        .comentarioEstadia(data.getComentarioEstadia())
                        .puntajeEstadia(data.getPuntajeEstadia())
                        .comentarioViajero(data.getComentarioViajero())
                        .puntajeViajero(data.getPuntajeViajero())
                        .build()).collect(Collectors.toList());

    }

    public List<CalificacionValidation> buscarPorNombreUsuario(String nombreUsuario){

        return calificacionService.buscarPorNombreUsuario(nombreUsuario).stream().map(data ->
                CalificacionValidation.builder().idCalificacion(data.getIdCalificacion().toString())
                        .idDisp(data.getDisponibilidadCasaEntity().getIdDisp().toString())
                        .comentarioEstadia(data.getComentarioEstadia())
                        .puntajeEstadia(data.getPuntajeEstadia())
                        .comentarioViajero(data.getComentarioViajero())
                        .puntajeViajero(data.getPuntajeViajero())
                        .build()).collect(Collectors.toList());

    }

    public List<CalificacionValidation> buscarPorIdCasa(String idCasa){

        return calificacionService.buscarPorIdCasa(Integer.parseInt(idCasa)).stream().map(data ->
                CalificacionValidation.builder().idCalificacion(data.getIdCalificacion().toString())
                        .idDisp(data.getDisponibilidadCasaEntity().getIdDisp().toString())
                        .comentarioEstadia(data.getComentarioEstadia())
                        .puntajeEstadia(data.getPuntajeEstadia())
                        .comentarioViajero(data.getComentarioViajero())
                        .puntajeViajero(data.getPuntajeViajero())
                        .build()).collect(Collectors.toList());

    }



}
