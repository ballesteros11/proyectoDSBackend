package com.rdai.domain.services;

import com.rdai.data.models.MainUsuario;
import com.rdai.data.models.entities.UsuarioEntity;
import com.rdai.data.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UsuarioDetalleService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        Optional<UsuarioEntity> user = usuarioRepo.findByNombreUsuario(userName);

        return user.map(MainUsuario::build).orElse(null);
    }

}
