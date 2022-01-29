package com.rdai.data.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class CalificacionEntity {

    @Id
    @Column(name = "id_calificacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCalificacion;

    @Column(length = 500)
    private String comentarioEstadia;

    private Integer puntajeEstadia;

    @Column(length = 500)
    private String comentarioViajero;

    private Integer puntajeViajero;

    @JoinColumn(name = "fk_reserva")
    @OneToOne
    private DisponibilidadCasaEntity disponibilidadCasaEntity;

    public Integer getIdCalificacion() {
        return idCalificacion;
    }

    public String getComentarioEstadia() {
        return comentarioEstadia;
    }

    public Integer getPuntajeEstadia() {
        return puntajeEstadia;
    }

    public String getComentarioViajero() {
        return comentarioViajero;
    }

    public Integer getPuntajeViajero() {
        return puntajeViajero;
    }

    public DisponibilidadCasaEntity getDisponibilidadCasaEntity() {
        return disponibilidadCasaEntity;
    }
}
