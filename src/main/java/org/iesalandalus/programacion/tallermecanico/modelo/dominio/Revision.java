package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Revision extends Trabajo {
    public static final float PRECIO_HORA = 35;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
        fechaFin = null;
        horas = 0;
    }

    @Override
    public float getPrecioEspecifico() {
        return 0;
    }

    public Revision(Revision revision) {
        super(revision);
    }
}