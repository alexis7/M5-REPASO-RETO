package com.bancolombia.aplicacionbancaria.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "historial_prestamo")
public class HistorialPrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prestamo_id")
    private Prestamo prestamo;

    @Column(name = "monto_solicitado", precision = 15, scale = 2, nullable = false)
    private BigDecimal montoSolicitado;

    @Column(length = 50, nullable = false)
    private String estado;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Prestamo getPrestamo() { return prestamo; }
    public void setPrestamo(Prestamo prestamo) { this.prestamo = prestamo; }

    public BigDecimal getMontoSolicitado() { return montoSolicitado; }
    public void setMontoSolicitado(BigDecimal montoSolicitado) { this.montoSolicitado = montoSolicitado; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }

    @Override
    public String toString() {
        return "HistorialPrestamo{" +
                ", Id del Prestamos=" + (prestamo != null ? prestamo.getId() : "null") +
                ", Monto Solicitado=" + montoSolicitado +
                ", Estado='" + estado + '\'' +
                ", Fecha Creación=" + fechaCreacion +
                ", Fecha Actualización=" + fechaActualizacion +
                '}';
    }

}