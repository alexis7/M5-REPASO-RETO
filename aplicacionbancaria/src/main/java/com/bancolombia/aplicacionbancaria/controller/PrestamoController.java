package com.bancolombia.aplicacionbancaria.controller;

import com.bancolombia.aplicacionbancaria.model.dto.ClienteDTO;
import com.bancolombia.aplicacionbancaria.model.dto.ConsultarPrestamo;
import com.bancolombia.aplicacionbancaria.model.dto.SolicitarPrestamoDTO;
import com.bancolombia.aplicacionbancaria.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    private PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }


    @GetMapping("/consultar")
    public String consultarPrestamo(@Valid @RequestBody ConsultarPrestamo prestamo) {
        return prestamoService.consultarEstadoPrestamo(prestamo.getIdPrestamo());
    }

    @GetMapping("/consultarHistorial")
    public String consultarHistorialPrestamo(@Valid @RequestBody ClienteDTO clienteDTO) {
        return prestamoService.consultarHistorialPrestamos(clienteDTO.getIdCliente());
    }

    @PostMapping("/solicitarPrestamo")
    public String solicitarPrestamo(@Valid @RequestBody SolicitarPrestamoDTO prestamoDTO) {
        return prestamoService.solicitarPrestamo(prestamoDTO);
    }

    @PostMapping("/aprobarPrestamo")
    public String aprobarPrestamo(@Valid @RequestBody ConsultarPrestamo prestamo) {
        return prestamoService.aprobarPrestamo(prestamo.getIdPrestamo());
    }

    @PostMapping("/rechazarPrestamo")
    public String rechazarPrestamo(@Valid @RequestBody ConsultarPrestamo prestamo) {
        return prestamoService.rechazarPrestamo(prestamo.getIdPrestamo());
    }

    @PostMapping("/simularCuota")
    public String simularCuotas(@Valid @RequestBody SolicitarPrestamoDTO prestamo) {
        return prestamoService.simularCuotas(prestamo);
    }
}
