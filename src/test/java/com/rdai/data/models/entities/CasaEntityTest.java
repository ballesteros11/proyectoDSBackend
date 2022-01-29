package com.rdai.data.models.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CasaEntityTest {

    CasaEntity house = new CasaEntity();

    private void createHouse(){
        house = CasaEntity.builder().idCasa(1).ciudad("Medellin").pais("Colombia")
                .telefono("2168546").direccion("cra 25 b 331 sur 82")
                .estado("Antioquia").foto(null).usuarioEntity(null).build();
    }


    @Test
    void verifyHouse(){

        createHouse();
        assertEquals(1, house.getIdCasa());
        assertEquals("Medellin", house.getCiudad());
        assertEquals("Colombia", house.getPais());
        assertEquals("2168546", house.getTelefono());
        assertNull(house.getFoto());
        assertNull(house.getUsuarioEntity());
        assertEquals("cra 25 b 331 sur 82", house.getDireccion());
        assertEquals("Antioquia", house.getEstado());

    }




}
