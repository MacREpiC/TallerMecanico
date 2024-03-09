package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

public enum FuenteDatosMemoria implements org.iesalandalus.programacion.tallermecanico.modelo.negocio.IFuenteDatos {
    MEMORIA;

    public IClientes crearClientes() {
        return new Clientes();
    }

    public IVehiculos crearVehiculos() {
        return new Vehiculos();
    }

    public ITrabajos crearTrabajos() {
        return new Trabajos();
    }
}

