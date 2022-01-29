package com.rdai.domain.services;

import com.rdai.data.models.entities.RolEntity;
import com.rdai.data.models.enums.NombreRol;
import com.rdai.data.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepo;


    public Optional<RolEntity> getByNombreRol(NombreRol nombreRol){

        return rolRepo.findByNombreRol(nombreRol);

    }

    public RolEntity crearRol(RolEntity rol){
        return rolRepo.save(rol);
    }

    public boolean existsByNombreRol(NombreRol nombreRol){
        return rolRepo.existsByNombreRol(nombreRol);
    }

}
