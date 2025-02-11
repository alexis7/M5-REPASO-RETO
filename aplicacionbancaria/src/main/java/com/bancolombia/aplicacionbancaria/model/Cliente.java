package com.bancolombia.aplicacionbancaria.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_documento", length = 50, nullable = false, unique = true)
    private String numeroDocumento;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String telefono;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String direccion;

    @OneToMany(mappedBy = "cliente")
    private List<Prestamo> prestamos;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }


    public List<Prestamo> getPrestamos(){

        int totalElementos = prestamos.size();
        prestamos.sort(Comparator.comparing(Prestamo::getFechaCreacion));
        int inicio = Math.max(totalElementos - 3, 0);
        List<Prestamo> historialList = new ArrayList<>();
        for (int i = inicio; i < totalElementos; i++) {
            historialList.add(prestamos.get(i));
        }
        return historialList;
    }

}
