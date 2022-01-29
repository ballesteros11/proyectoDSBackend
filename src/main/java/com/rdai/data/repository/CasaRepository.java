package com.rdai.data.repository;


import com.rdai.data.models.entities.CasaEntity;
import com.rdai.data.repository.custom.CriterioCasaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasaRepository extends JpaRepository<CasaEntity, Integer>, CriterioCasaRepository {

    List<CasaEntity> findAllByPais(String pais);

    List<CasaEntity> findAllByEstado(String estado);

    List<CasaEntity> findAllByCiudad(String ciudad);

    List<CasaEntity> findAllByUsuarioEntity_NombreUsuario(String nombreUsuario);

}
