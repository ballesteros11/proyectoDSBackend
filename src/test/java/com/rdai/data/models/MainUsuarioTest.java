package com.rdai.data.models;

import com.rdai.data.models.entities.RolEntity;
import com.rdai.data.models.entities.UsuarioEntity;
import com.rdai.data.models.enums.NombreRol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainUsuarioTest {

    @InjectMocks
    MainUsuario mainUsuario;

    UsuarioEntity usuarioEntity;

    @BeforeEach
    void setUp(){
        usuarioEntity = crearUsuarioTest().get();
        MockitoAnnotations.openMocks(this);
    }

    private Optional<UsuarioEntity> crearUsuarioTest() {
        Set<RolEntity> roles = new HashSet<>();
        roles.add(new RolEntity(1, NombreRol.ANFITRION));
        roles.add(new RolEntity(2, NombreRol.VIAJERO));

        return Optional.of( UsuarioEntity.builder().id(1).nombreUsuario("gustavo")
                .password("321654").nombreCompleto("Gustavo").pais("Colombia")
                .ciudad("Medellin").rolEntity(roles).build() );
    }

    @Test
    void build() {
        assertNotNull(MainUsuario.build(usuarioEntity));
        mainUsuario = MainUsuario.build(usuarioEntity);

        assertEquals(usuarioEntity.getNombreUsuario(), mainUsuario.getNombreUsuario());
        assertEquals(usuarioEntity.getNombreCompleto(), mainUsuario.getNombreCompleto());
        assertEquals(usuarioEntity.getCiudad(), mainUsuario.getCiudad());
        assertEquals(usuarioEntity.getPais(), mainUsuario.getPais());
        assertEquals(usuarioEntity.getPassword(), mainUsuario.getPassword());
        assertEquals(usuarioEntity.getRolEntity().size(),
                (long) mainUsuario.getAuthorities().size());

        assertEquals(usuarioEntity.getNombreUsuario(), mainUsuario.getUsername());
        assertTrue(mainUsuario.isAccountNonExpired());
        assertTrue(mainUsuario.isAccountNonLocked());
        assertTrue(mainUsuario.isCredentialsNonExpired());
        assertTrue(mainUsuario.isEnabled());

    }

}
