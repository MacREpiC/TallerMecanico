package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Mecanico extends Trabajo {
    private float FACTOR_HORA = 30f;
    private float FACTOR_PRECIO_MATERIAL = 1.5f;
    private float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        super(cliente, vehiculo, fechaInicio);
        fechaFin = null;
        horas = 0;
        precioMaterial = 0;
    }

    @Override
    public float getPrecioEspecifico() {
        return 0;
    }

    public Mecanico(Mecanico mecanico) {
        super(mecanico);
        this.precioMaterial = mecanico.precioMaterial;
    }
}
