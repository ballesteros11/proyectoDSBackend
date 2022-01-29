package com.rdai.data.repository;

import com.rdai.data.models.entities.CalificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository<CalificacionEntity, Integer> {

    List<CalificacionEntity> findAllByDisponibilidadCasaEntity_CasaEntity_IdCasa(Integer idCasa);

    List<CalificacionEntity> findAllByDisponibilidadCasaEntity_UsuarioReservado_NombreUsuario(String nombreUsuario);

    List<CalificacionEntity> findAllByDisponibilidadCasaEntity_IdDisp(Integer idDisp);
}
