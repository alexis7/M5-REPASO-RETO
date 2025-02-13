### Los endpoint para probar son los siguientes:

### Para consultar el prestamos:
localhost:8080\prestamo\consultar

{
"idPrestamo": "4"
}

### Para consultar el historial de prestamos de un cliente:
localhost:8080\prestamo\consultarHistorial

{
"idCliente": "1"
}

### Para solicitar un prestamo:
localhost:8080\prestamo\solicitarPrestamo

{
"monto": "1900",
"clienteId":"1",
"duracionMeses": "12",
"interes":"1.2"
}

### Para aprobar un prestamo:
localhost:8080\prestamo\aprobarPrestamo

{
"idPrestamo": "7"
}

### Para recharzar un prestamo:
localhost:8080\prestamo\rechazarPrestamo

{
"idPrestamo": "4"
}

### Para simular la cuota de un prestamo:
localhost:8080\prestamo\simularCuota

{
"monto": "1900",
"clienteId":"1",
"duracionMeses": "12",
"interes":"12"
}


### Script de las tablas de la DB


-- Crear la tabla cliente

CREATE TABLE public.cliente (
id SERIAL PRIMARY KEY,
numero_documento VARCHAR(50) NOT NULL UNIQUE,
nombre VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL UNIQUE,
telefono VARCHAR(20) NOT NULL,
direccion TEXT NOT NULL
);


-- Crear la tabla prestamos

CREATE TABLE public.prestamos (
id SERIAL PRIMARY KEY,
monto NUMERIC(12,2) NOT NULL,
interes NUMERIC(5,2) NOT NULL,
duracion_meses INTEGER NOT NULL,
estado VARCHAR(20) NOT NULL,
cliente_id INTEGER NOT NULL,
fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id)
    REFERENCES public.cliente (id)
    ON DELETE CASCADE,

    CONSTRAINT prestamo_estado_check CHECK (estado IN ('Pendiente', 'Aprobado', 'Rechazado'))
);
    