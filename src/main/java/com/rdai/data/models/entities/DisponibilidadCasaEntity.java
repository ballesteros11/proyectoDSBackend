package com.rdai.data.models.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class DisponibilidadCasaEntity {

    @Id
    @Column(name = "id_disp")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDisp;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @JoinColumn(name = "fk_casa")
    @ManyToOne
    private CasaEntity casaEntity;

    @JoinColumn(name = "fk_usuario_reservado")
    @ManyToOne
    private UsuarioEntity usuarioReservado;

    public Integer getIdDisp() {
        return idDisp;
    }

    public UsuarioEntity getUsuarioReservado() {
        return usuarioReservado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public CasaEntity getCasaEntity() {
        return casaEntity;
    }
}
