package com.bancolombia.aplicacionbancaria.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ConsultarPrestamo {

    @NotNull(message = "El id del prestamos no puede ser nulo")
    @Positive(message = "El id del prestamos debe ser mayor a cero")
    private Long idPrestamo;

    public ConsultarPrestamo(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public ConsultarPrestamo() {
    }

    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
}
