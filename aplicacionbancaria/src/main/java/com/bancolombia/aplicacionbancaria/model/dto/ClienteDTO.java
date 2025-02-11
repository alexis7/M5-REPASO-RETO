package com.bancolombia.aplicacionbancaria.model.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;


@Validated
public class ClienteDTO {

    @NotNull(message = "El numero de documento no puede ser nulo")
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
