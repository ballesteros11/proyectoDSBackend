package com.rdai.domain.services;


import com.rdai.data.models.entities.UsuarioEntity;
import com.rdai.data.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepo;

    public UsuarioEntity crearUsuario (UsuarioEntity usuarioEntity) {
        return usuarioRepo.save(usuarioEntity);
    }

    public Optional<UsuarioEntity> getByUsername(String username){
        return usuarioRepo.findByNombreUsuario(username);
    }

    public boolean existsByUsername(String username){
        return usuarioRepo.existsByNombreUsuario(username);
    }

}
