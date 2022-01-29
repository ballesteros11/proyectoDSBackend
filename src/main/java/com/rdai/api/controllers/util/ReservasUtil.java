package com.rdai.api.controllers.util;

import com.rdai.api.validations.DisponibilidadValidation;
import com.rdai.configuration.jwt.JwtProvider;
import com.rdai.data.models.entities.CasaEntity;
import com.rdai.data.models.entities.DisponibilidadCasaEntity;
import com.rdai.data.models.entities.UsuarioEntity;
import com.rdai.domain.services.BusquedaCasaService;
import com.rdai.domain.services.DisponibilidadCasaService;
import com.rdai.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class ReservasUtil {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private BusquedaCasaService bcasaService;

    @Autowired
    private DisponibilidadCasaService dispService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public UsuarioEntity buscarUser(String username){
        return usuarioService.getByUsername(username).orElse(null);
    }

    public boolean validarFechas(DisponibilidadValidation dispValidation){


        LocalDate fechaInicio = LocalDate.parse(dispValidation.getFechaInicio(), formatter);
        LocalDate fechaFin = LocalDate.parse(dispValidation.getFechaFin(), formatter);

        LocalDate fechaActual = LocalDate.now();

        return !fechaActual.isAfter(fechaInicio) && !fechaActual.isAfter(fechaFin);
    }


    public boolean verificarDisponibilidad(DisponibilidadValidation dispValidation){
        Integer id = Integer.valueOf(dispValidation.getIdCasa());

        LocalDate fechaInicio = LocalDate.parse(dispValidation.getFechaInicio(), formatter);
        LocalDate fechaFin = LocalDate.parse(dispValidation.getFechaFin(), formatter);

        return dispService.verificarDisponibilidad(id, fechaInicio, fechaFin);

    }

    public DisponibilidadCasaEntity crearReserva(DisponibilidadValidation disponibilidadValidation, String token){

        token = token.split(" ")[1];

        String username = jwtProvider.getUserNameFromToken(token);

        LocalDate fechaInicio = LocalDate.parse(disponibilidadValidation.getFechaInicio(), formatter);
        LocalDate fechaFin = LocalDate.parse(disponibilidadValidation.getFechaFin(), formatter);

        UsuarioEntity usuarioReserva = buscarUser(username);

        CasaEntity casa = bcasaService.getById(Integer.parseInt(disponibilidadValidation.getIdCasa())).orElseGet(null);

        return DisponibilidadCasaEntity.builder()
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .idDisp(null)
                .casaEntity(casa)
                .usuarioReservado(usuarioReserva).build();
    }


}
