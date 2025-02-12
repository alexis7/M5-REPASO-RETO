package com.bancolombia.aplicacionbancaria.service;

import com.bancolombia.aplicacionbancaria.model.Cliente;
import com.bancolombia.aplicacionbancaria.model.Prestamo;
import com.bancolombia.aplicacionbancaria.model.dto.SolicitarPrestamoDTO;
import com.bancolombia.aplicacionbancaria.repository.ClienteRepository;
import com.bancolombia.aplicacionbancaria.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrestamoService {

    private PrestamoRepository prestamoRepository;

    private ClienteRepository clienteRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, ClienteRepository clienteRepository) {
        this.prestamoRepository = prestamoRepository;
        this.clienteRepository = clienteRepository;
    }

    public String consultarEstadoPrestamo(Long idPrestamo) {
        Optional<Prestamo> prestamo = prestamoRepository.findById(idPrestamo);
        if (prestamo.isPresent()) {
            return "El estado del prestamo es: " + prestamo.get().getEstado();
        }
        throw new NullPointerException("El prestamo no existe");
    }

    public String consultarHistorialPrestamos(Long idCliente){
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        if (cliente.isPresent()) {
            return "Historial de prestamos del cliente: " + cliente.get().getPrestamos();
        }
        throw new NullPointerException("El cliente no existe");
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

                        return "Prestamo Rechazado";
                    } else {
                        return "Prestamo no Rechazado, cuenta con el estado " + prestamo.getEstado();
                    }
                })
                .orElseThrow(() -> new NullPointerException("El prestamo no existe"));
    }

    public String simularCuotas(SolicitarPrestamoDTO prestamoDTO) {
        Prestamo prestamo = new Prestamo();
        return prestamo.calcularCuota(prestamoDTO.getMonto(), prestamoDTO.getInteres(), prestamoDTO.getDuracionMeses());
    }

}
