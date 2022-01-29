package com.rdai.api.controllers.util;

import com.rdai.api.validations.DisponibilidadValidation;
import com.rdai.domain.services.DisponibilidadCasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaSearchUtil {

    @Autowired
    private DisponibilidadCasaService reservaService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<DisponibilidadValidation> obtenerReservasPorNombreUsuario(String nombreUsuario){
        return reservaService.findAllByUsuario(nombreUsuario).stream().map(data ->
                DisponibilidadValidation.builder().usuarioReservado(data.getUsuarioReservado().getNombreUsuario())
                        .idCasa(data.getCasaEntity().getIdCasa().toString())
                        .fechaInicio(data.getFechaInicio().format(formatter))
                        .fechaFin(data.getFechaFin().format(formatter))
                        .idDisp(data.getIdDisp().toString())
                        .build()).collect(Collectors.toList());
    }


    public List<DisponibilidadValidation> obtenerReservasPorIdCasa(String idCasa){
        Integer id = Integer.parseInt(idCasa);
        return reservaService.findAllByCasa(id).stream().map(data ->
                DisponibilidadValidation.builder().usuarioReservado(data.getUsuarioReservado().getNombreUsuario())
                        .idCasa(data.getCasaEntity().getIdCasa().toString())
                        .fechaInicio(data.getFechaInicio().format(formatter))
                        .fechaFin(data.getFechaFin().format(formatter))
                        .idDisp(data.getIdDisp().toString())
                        .build()).collect(Collectors.toList());
    }


}
