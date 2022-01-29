package com.rdai.data.models;

import com.rdai.data.models.entities.UsuarioEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;


@AllArgsConstructor
public class MainUsuario implements UserDetails {

    private String nombreUsuario;
    private String password;
    private String nombreCompleto;
    private String ciudad;
    private String pais;
    private Collection<? extends GrantedAuthority> authorities;

    public static MainUsuario build(UsuarioEntity usuarioEntity) {
        List<GrantedAuthority> authorities = usuarioEntity.getRolEntity().stream()
                .map(rolEntity -> new SimpleGrantedAuthority(rolEntity.getNombreRol().name()))
                .collect(Collectors.toList());
        return new MainUsuario(usuarioEntity.getNombreUsuario(), usuarioEntity.getPassword(),
                usuarioEntity.getNombreCompleto(), usuarioEntity.getCiudad(), usuarioEntity.getPais(), authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }



}
