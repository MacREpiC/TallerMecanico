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
        String cadenaADevolver;
        if (!estaCerrado()) {
            cadenaADevolver = String.format("Revisión -> %s - %s (%s - ): %d horas", cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), horas);
        } else {
            cadenaADevolver = String.format("Revisión -> %s - %s (%s - %s): %d horas, %.2f € total", cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), fechaFin.format(FORMATO_FECHA), horas, getPrecio());
        }
        return cadenaADevolver;
    }
}