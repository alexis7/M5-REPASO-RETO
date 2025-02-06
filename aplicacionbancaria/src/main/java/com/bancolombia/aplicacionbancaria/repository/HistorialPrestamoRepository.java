package com.bancolombia.aplicacionbancaria.repository;

import com.bancolombia.aplicacionbancaria.model.HistorialPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialPrestamoRepository extends JpaRepository<HistorialPrestamo, Long> {
}
