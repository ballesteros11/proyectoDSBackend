package com.rdai.api.validations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalificacionValidation {

    private String idCalificacion;

    private Integer puntajeEstadia;

    private String comentarioEstadia;

    private Integer puntajeViajero;

    private String comentarioViajero;

    private String idDisp;

    public String getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(String idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Integer getPuntajeEstadia() {
        return puntajeEstadia;
    }

    public void setPuntajeEstadia(Integer puntajeEstadia) {
        this.puntajeEstadia = puntajeEstadia;
    }

    public String getComentarioEstadia() {
        return comentarioEstadia;
    }

    public void setComentarioEstadia(String comentarioEstadia) {
        this.comentarioEstadia = comentarioEstadia;
    }

    public Integer getPuntajeViajero() {
        return puntajeViajero;
    }

    public void setPuntajeViajero(Integer puntajeViajero) {
        this.puntajeViajero = puntajeViajero;
    }

    public String getComentarioViajero() {
        return comentarioViajero;
    }

    public void setComentarioViajero(String comentarioViajero) {
        this.comentarioViajero = comentarioViajero;
    }

    public String getIdDisp() {
        return idDisp;
    }

    public void setIdDisp(String idDisp) {
        this.idDisp = idDisp;
    }
}
