### Los endpoint para probar son los siguientes:

### Para consultar el saldo de una cuenta:
localhost:8080/cuenta/saldo

{
"cuenta": "12"
}

### Para consultar el historial de transacciones de una cuenta:
localhost:8080/cuenta/historial

{
"cuenta": "12"
}

### Para realizar un deposito desde una sucursal:
localhost:8080/transaccion/deposito/sucursal

{
"cuenta": "12",
"tipoTransaccion": "Deposito desde sucursal",
"monto": 100
}

### Para realizar un deposito desde un cajero:
localhost:8080/transaccion/deposito/cajero

{
"cuenta": "12",
"tipoTransaccion": "Deposito desde cajero",
"monto": 100
}

### Para realizar un deposito desde otra cuenta:
localhost:8080/transaccion/deposito/otracuenta

{
"cuenta": "12",
"tipoTransaccion": "Deposito desde otra cuenta",
"monto": 100
}

### Para realizar una compra en un establecimiento fisico:
localhost:8080/transaccion/compra/fisico

{
"cuenta": "12",
"tipoTransaccion": "Compra establecimiento fisico",
"monto": 100
}

### Para realizar una compra en una pagina web:
localhost:8080/transaccion/compra/web

{
"cuenta": "12",
"tipoTransaccion": "Compra en pagina web",
"monto": 100
}

### Para realizar un retiro en un cajero:
localhost:8080/transaccion/retiro/cajero

{
"cuenta": "12",
"tipoTransaccion": "Retiro desde cajero",
"monto": 100
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
    