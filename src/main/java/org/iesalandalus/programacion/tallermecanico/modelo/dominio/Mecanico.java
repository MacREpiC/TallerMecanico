package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
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

    public Mecanico(Mecanico mecanico) {
        super(mecanico);
        this.precioMaterial = mecanico.precioMaterial;
    }

    public float getPrecioMaterial(){
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if(precioMaterial <= 0){
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (estaCerrado()) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        this.precioMaterial += precioMaterial;
    }

    @Override
    public float getPrecioEspecifico() {
        return (FACTOR_HORA * getHoras()) + (FACTOR_PRECIO_MATERIAL * precioMaterial);
    }

    @Override
    public String toString() {
        return String.format("Mecanico[FACTOR_HORA=%s, FACTOR_PRECIO_MATERIAL=%s, precioMaterial=%s, fechaInicio=%s, fechaFin=%s, horas=%s, cliente=%s, vehiculo=%s]", this.FACTOR_HORA, this.FACTOR_PRECIO_MATERIAL, this.precioMaterial, this.fechaInicio, this.fechaFin, this.horas, this.cliente, this.vehiculo);
    }
}
