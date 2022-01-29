package com.rdai.domain.services;

import com.rdai.data.models.entities.CalificacionEntity;
import com.rdai.data.repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;


    public List<CalificacionEntity> buscarPorIdCasa(Integer idCasa){
        return calificacionRepository.findAllByDisponibilidadCasaEntity_CasaEntity_IdCasa(idCasa);
    }

    public List<CalificacionEntity> buscarPorNombreUsuario(String nombreUsuario){
        return calificacionRepository.findAllByDisponibilidadCasaEntity_UsuarioReservado_NombreUsuario(nombreUsuario);
    }

    public CalificacionEntity guardarCalificacion(CalificacionEntity calificacion){
        return calificacionRepository.save(calificacion);
    }

    public List<CalificacionEntity> buscarPorIdReserva(Integer idReserva){
        return calificacionRepository.findAllByDisponibilidadCasaEntity_IdDisp(idReserva);
    }
}
