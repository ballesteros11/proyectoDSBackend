package com.rdai.data.models.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioEntityTest {

    UsuarioEntity user = new UsuarioEntity();

    private void createUser() {
        user = UsuarioEntity.builder().nombreUsuario("gustavo").password("123456789").build();
    }

    @Test
    void chargeEntity(){
        createUser();
        assertEquals("gustavo", user.getNombreUsuario());
        assertEquals("123456789", user.getPassword());
    }

}
