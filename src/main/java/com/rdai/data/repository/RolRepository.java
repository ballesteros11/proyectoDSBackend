package com.rdai.data.repository;


import com.rdai.data.models.entities.RolEntity;
import com.rdai.data.models.enums.NombreRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Integer> {

    Optional<RolEntity> findByNombreRol (NombreRol nombreRol);
    
    boolean existsByNombreRol(NombreRol nombreRol);

}
