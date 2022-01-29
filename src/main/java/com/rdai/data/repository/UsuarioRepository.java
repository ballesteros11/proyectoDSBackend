package com.rdai.data.repository;

import com.rdai.data.models.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {


    Optional<UsuarioEntity> findByNombreUsuario(String nombreUsuario);


    boolean existsByNombreUsuario(String nombreUsuario);

}
