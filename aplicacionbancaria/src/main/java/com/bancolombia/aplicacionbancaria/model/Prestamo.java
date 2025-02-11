package com.bancolombia.aplicacionbancaria.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal interes;

    @Column(nullable = false)
    private Integer duracionMeses;

    @Column(nullable = false, length = 20)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "prestamo")
    private List<HistorialPrestamo> historialPrestamo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public BigDecimal getInteres() { return interes; }
    public void setInteres(BigDecimal interes) { this.interes = interes; }

    public Integer getDuracionMeses() { return duracionMeses; }
    public void setDuracionMeses(Integer duracionMeses) { this.duracionMeses = duracionMeses; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }

    public List<HistorialPrestamo> getHistorialPrestamos() {
        int totalElementos = historialPrestamo.size();
        historialPrestamo.sort(Comparator.comparing(HistorialPrestamo::getFechaCreacion));
        int inicio = Math.max(totalElementos - 5, 0);
        List<HistorialPrestamo> historialList = new ArrayList<>();
        for (int i = inicio; i < totalElementos; i++) {
            historialList.add(historialPrestamo.get(i));
        }
        return historialList;
    }

    //Fórmula de la Cuota Mensual (Sistema Francés)
        public String calcularCuota(BigDecimal monto, BigDecimal tasaAnual, int meses) {
            if (meses <= 0 || monto.compareTo(BigDecimal.ZERO) <= 0 || tasaAnual.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Los valores deben ser mayores a 0.");
            }

            // Convertimos la tasa anual a tasa mensual
            BigDecimal tasaMensual = tasaAnual.divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_EVEN);

            // Fórmula de cuota fija
            BigDecimal unoMasTasa = BigDecimal.ONE.add(tasaMensual);
            BigDecimal potencia = unoMasTasa.pow(-meses, new MathContext(10, RoundingMode.HALF_EVEN));
            BigDecimal divisor = BigDecimal.ONE.subtract(potencia);

            return "El valor de la cuota será de: " + monto.multiply(tasaMensual).divide(divisor, 2, RoundingMode.HALF_EVEN);
        }

        @Override
        public String toString() {
            return "Prestamo { " +
                    "id=" + id +
                    ", monto=" + monto +
                    ", interes=" + interes +
                    ", duracionMeses=" + duracionMeses +
                    ", estado='" + estado + '\'' +
                    ", fechaCreacion=" + fechaCreacion +
                    ", fechaActualizacion=" + fechaActualizacion +
                    " }";
        }

}
