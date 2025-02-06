package com.bancolombia.aplicacionbancaria.service;

import com.bancolombia.aplicacionbancaria.model.HistorialPrestamo;
import com.bancolombia.aplicacionbancaria.model.Prestamo;
import com.bancolombia.aplicacionbancaria.model.dto.SolicitarPrestamoDTO;
import com.bancolombia.aplicacionbancaria.repository.ClienteRepository;
import com.bancolombia.aplicacionbancaria.repository.HistorialPrestamoRepository;
import com.bancolombia.aplicacionbancaria.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrestamoService {

    private PrestamoRepository prestamoRepository;

    private ClienteRepository clienteRepository;

    private HistorialPrestamoRepository historialPrestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, ClienteRepository clienteRepository,
                           HistorialPrestamoRepository historialPrestamoRepository) {
        this.prestamoRepository = prestamoRepository;
        this.clienteRepository = clienteRepository;
        this.historialPrestamoRepository = historialPrestamoRepository;
    }

    public String consultarEstadoPrestamo(Long idPrestamo) {
        Optional<Prestamo> prestamo = prestamoRepository.findById(idPrestamo);
        if (prestamo.isPresent()) {
            return "El estado del prestamo es: " + prestamo.get().getEstado();
        }
        throw new NullPointerException("El prestamo no existe");
    }

    public String consultarHistorialPrestamos(Long idPrestamo) {
        Optional<Prestamo> prestamo = prestamoRepository.findById(idPrestamo);
        if (prestamo.isPresent()) {
            return "Historial de los ultimos 5 prestamos: " + prestamo.get().getHistorialPrestamos();
        }
        throw new NullPointerException("El prestamo no existe");
    }

    public String solicitarPrestamo(SolicitarPrestamoDTO prestamoDTO) {
        return clienteRepository.findById(prestamoDTO.getClienteId())
                .map(cliente -> {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setCliente(cliente);
                    prestamo.setMonto(prestamoDTO.getMonto());
                    prestamo.setEstado("Pendiente");
                    prestamo.setInteres(prestamoDTO.getInteres());
                    prestamo.setDuracionMeses(prestamoDTO.getDuracionMeses());
                    prestamoRepository.save(prestamo);

                    HistorialPrestamo historialPrestamo = new HistorialPrestamo();
                    historialPrestamo.setMontoSolicitado(prestamoDTO.getMonto());
                    historialPrestamo.setPrestamo(prestamo);
                    historialPrestamo.setEstado("Pendiente");
                    historialPrestamoRepository.save(historialPrestamo);

                    return "Prestamo solicitado";
                })
                .orElseThrow(() -> new NullPointerException("El cliente no existe"));
    }

    public String aprobarPrestamo(Long idPrestamo) {
        return prestamoRepository.findById(idPrestamo)
                .map(prestamo -> {
                    if ("Pendiente".equals(prestamo.getEstado())) {
                        prestamo.setEstado("Aprobado");
                        prestamoRepository.save(prestamo);

                        HistorialPrestamo historialPrestamo = new HistorialPrestamo();
                        historialPrestamo.setMontoSolicitado(prestamo.getMonto());
                        historialPrestamo.setPrestamo(prestamo);
                        historialPrestamo.setEstado("Aprobado");
                        historialPrestamoRepository.save(historialPrestamo);

                        return "Prestamo aprobado";
                    } else {
                        return "Prestamo no aprobado, cuenta con el estado " + prestamo.getEstado();
                    }
                })
                .orElseThrow(() -> new NullPointerException("El prestamo no existe"));
    }

    public String rechazarPrestamo(Long idPrestamo) {
        return prestamoRepository.findById(idPrestamo)
                .map(prestamo -> {
                    if ("Pendiente".equals(prestamo.getEstado())) {
                        prestamo.setEstado("Rechazado");
                        prestamoRepository.save(prestamo);

                        HistorialPrestamo historialPrestamo = new HistorialPrestamo();
                        historialPrestamo.setMontoSolicitado(prestamo.getMonto());
                        historialPrestamo.setPrestamo(prestamo);
                        historialPrestamo.setEstado("Rechazado");
                        historialPrestamoRepository.save(historialPrestamo);

                        return "Prestamo Rechazado";
                    } else {
                        return "Prestamo no Rechazado, cuenta con el estado " + prestamo.getEstado();
                    }
                })
                .orElseThrow(() -> new NullPointerException("El prestamo no existe"));
    }

    public String simularCuotas() {
        return "Rechazando prestamo";
    }

}
