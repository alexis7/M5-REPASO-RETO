package com.bancolombia.aplicacionbancaria.model.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;


@Validated
public class ClienteDTO {

    @NotNull(message = "El numero de documento no puede ser nulo")
    private String numeroDocumento;


    public ClienteDTO(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public ClienteDTO() {
    }


}
