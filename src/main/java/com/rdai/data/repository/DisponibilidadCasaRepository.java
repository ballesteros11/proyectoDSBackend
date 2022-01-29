package com.rdai.data.repository;

import com.rdai.data.models.entities.DisponibilidadCasaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface

DisponibilidadCasaRepository extends JpaRepository<DisponibilidadCasaEntity,Integer> {

    @Query(
            "SELECT d FROM DisponibilidadCasaEntity d WHERE d.casaEntity.idCasa = ?1 AND " +
                    "(d.fechaInicio BETWEEN ?2 AND ?3) AND (d.fechaFin BETWEEN ?2 AND ?3)"
    )
    List<DisponibilidadCasaEntity> findAllByCasaReservadaAndBetweenDate
            (Integer idCasa, LocalDate fechaInicio, LocalDate fechaFin);


    List<DisponibilidadCasaEntity> findAllByCasaEntity_IdCasa(Integer idCasa);

    List<DisponibilidadCasaEntity> findAllByUsuarioReservado_NombreUsuario(String nombreUsuario);
}
