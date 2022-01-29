package com.rdai.api.controllers.util;


import com.rdai.api.validations.CasaValidation;
import com.rdai.api.validations.SearchValidation;
import com.rdai.domain.services.BusquedaCasaService;
import com.rdai.domain.services.CalificacionService;
import com.rdai.domain.services.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManejoBusquedasUtil {

    @Autowired
    private BusquedaCasaService bCasaService;

    @Autowired
    private CasaService casaService;

    @Autowired
    private CalificacionService calificacionService;

    public List<CasaValidation> buscandoCasas(){

        List<CasaValidation> result = new ArrayList<>();

        bCasaService.getAll().forEach( casa -> {
            CasaValidation casaValidation = CasaValidation.builder().idCasa(casa.getIdCasa().toString())
                    .idPropietario(casa.getUsuarioEntity().getNombreUsuario()).pais(casa.getPais())
                    .estado(casa.getEstado()).ciudad(casa.getCiudad()).telefono(casa.getTelefono())
                    .direccion(casa.getDireccion()).foto(casa.getFoto()).build();
            result.add(casaValidation);
        });
        return result;

    }

    public CasaValidation buscarCasaPorId(String id){
        return bCasaService.getById(Integer.parseInt(id)).map(casa -> {
            return CasaValidation.builder().idCasa(casa.getIdCasa().toString())
                    .idPropietario(casa.getUsuarioEntity().getNombreUsuario()).pais(casa.getPais())
                    .estado(casa.getEstado()).ciudad(casa.getCiudad()).telefono(casa.getTelefono())
                    .direccion(casa.getDireccion()).foto(casa.getFoto()).build();
        }).orElseGet(null);
    }

    public List<CasaValidation> busquedaPorCriterio(SearchValidation searchValidation){

        return bCasaService.getByCriterial(searchValidation).stream().map(casa ->
            CasaValidation.builder().idCasa(casa.getIdCasa().toString())
                    .idPropietario(casa.getUsuarioEntity().getNombreUsuario()).pais(casa.getPais())
                    .estado(casa.getEstado()).ciudad(casa.getCiudad()).telefono(casa.getTelefono())
                    .direccion(casa.getDireccion()).foto(casa.getFoto()).build()).collect(Collectors.toList());

    }


    public List<CasaValidation> busquedaPorPropietarioConSesionIniciada(String username){
        return casaService.findAllByPropietario(username).stream().map(casa ->
                CasaValidation.builder().idCasa(casa.getIdCasa().toString())
                        .idPropietario(casa.getUsuarioEntity().getNombreUsuario()).pais(casa.getPais())
                        .estado(casa.getEstado()).ciudad(casa.getCiudad()).telefono(casa.getTelefono())
                        .direccion(casa.getDireccion()).foto(casa.getFoto()).build()).collect(Collectors.toList());
    }


}
