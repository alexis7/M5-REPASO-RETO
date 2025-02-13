package com.bancolombia.aplicacionbancaria.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;


@Validated
public class ClienteDTO {

    @NotNull(message = "El id del cliente no puede ser nulo")
    @Positive(message = "El id del cliente debe ser mayor a cero")
    private Long idCliente;


    public ClienteDTO(String numeroDocumento) {
        this.idCliente = idCliente;
    }

    public ClienteDTO() {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }


}
