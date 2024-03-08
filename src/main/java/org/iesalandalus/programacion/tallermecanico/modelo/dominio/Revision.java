package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Revision extends Trabajo {
    public static final float PRECIO_HORA = 35;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
        fechaFin = null;
        horas = 0;
    }

    public Revision(Revision revision) {
        super(revision);
    }

    @Override
    public float getPrecioEspecifico() {
        return PRECIO_HORA * getHoras();
    }

    @Override
    public String toString() {
        return String.format("Revision[fechaInicio=%s, fechaFin=%s, horas=%s, cliente=%s, vehiculo=%s]", this.fechaInicio, this.fechaFin, this.horas, this.cliente, this.vehiculo);
    }
}