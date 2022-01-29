package com.rdai.domain.services;

import com.rdai.data.models.entities.CasaEntity;
import com.rdai.data.models.entities.DisponibilidadCasaEntity;
import com.rdai.data.models.entities.UsuarioEntity;
import com.rdai.data.repository.DisponibilidadCasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DisponibilidadCasaService {


    @Autowired
    private DisponibilidadCasaRepository dispRepo;


    public DisponibilidadCasaEntity guardarReserva(DisponibilidadCasaEntity nuevaReserva){
        return dispRepo.save(nuevaReserva);
    }

    public boolean verificarDisponibilidad(Integer idCasa, LocalDate fechaInicio, LocalDate fechaFin){
        List<DisponibilidadCasaEntity> reservas = dispRepo.findAllByCasaReservadaAndBetweenDate(idCasa, fechaInicio, fechaFin);
        return reservas.isEmpty();
    }

    public List<DisponibilidadCasaEntity> findAllByCasa(Integer idCasa){
        return dispRepo.findAllByCasaEntity_IdCasa(idCasa);
    }

    public List<DisponibilidadCasaEntity> findAllByUsuario(String userName){
        return dispRepo.findAllByUsuarioReservado_NombreUsuario(userName);
    }

    public Optional<DisponibilidadCasaEntity> findByID(String id){
        return dispRepo.findById(Integer.parseInt(id));
    }

}
