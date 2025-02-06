package com.bancolombia.aplicacionbancaria.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Validated
public class SolicitarPrestamoDTO {

    private Long id;
    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser mayor a cero")
    private BigDecimal monto;
    @NotNull(message = "El interes no puede ser nulo")
    @Positive(message = "El interes debe ser mayor a cero")
    private BigDecimal interes;
    @NotNull(message = "La duracion en meses no puede ser nula")
    @Positive(message = "La duración debe ser mayor a cero")
    private Integer duracionMeses;
    private String estado;

    @NotNull(message = "El id del cliente no puede ser nulo")
    private Long clienteId;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    // Constructor vacío (necesario para frameworks como Jackson)
    public SolicitarPrestamoDTO() {
    }

    // Constructor con parámetros
    public SolicitarPrestamoDTO(Long id, BigDecimal monto, BigDecimal interes, Integer duracionMeses,
                                String estado, Long clienteId,
                                LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.id = id;
        this.monto = monto;
        this.interes = interes;
        this.duracionMeses = duracionMeses;
        this.estado = estado;
        this.clienteId = clienteId;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public Integer getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(Integer duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

}
