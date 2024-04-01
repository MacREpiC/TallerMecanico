package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class Trabajo {
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected static final float FACTOR_DIA = 10;
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected int horas;
    protected Cliente cliente;
    protected Vehiculo vehiculo;

    protected Trabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        horas = 0;
    }

    protected Trabajo(Trabajo trabajo){
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        this.cliente = new Cliente(cliente);
        this.vehiculo = trabajo.vehiculo;
        this.fechaInicio = trabajo.fechaInicio;
        this.fechaFin = trabajo.fechaFin;
        this.horas = trabajo.horas;

    }

    public static Trabajo copiar(Trabajo trabajo){
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        if(trabajo instanceof Revision revision) {
            trabajo = new Revision(revision);
        } else if (trabajo instanceof Mecanico mecanico) {
            trabajo = new Mecanico(mecanico);
        }
        return trabajo;
    }

    public static Trabajo get(Vehiculo vehiculo) {
        return new Revision(new Cliente("Alejandro", "123456789A", "678897823"), vehiculo, LocalDate.now());
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    protected void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    protected void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        } else if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public void anadirHoras(int horas) throws OperationNotSupportedException {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        if (estaCerrado()) {
            throw new OperationNotSupportedException("No se puede añadir horas, ya que el trabajo está cerrado.");
        }
        this.horas += horas;
    }

    public boolean estaCerrado() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        if (estaCerrado()) {
            throw new OperationNotSupportedException("El trabajo ya está cerrado.");
        }
        setFechaFin(fechaFin);
    }

    public float getPrecio() {
        float precioFijo = FACTOR_DIA * getDias();
        float precioEspecifico = getPrecioEspecifico();
        return precioFijo + precioEspecifico;
    }

    public abstract float getPrecioEspecifico();

    private float getDias() {
        return (estaCerrado()) ? ChronoUnit.DAYS.between(fechaInicio, fechaFin) : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trabajo trabajo)) return false;
        return horas == trabajo.horas && Objects.equals(fechaInicio, trabajo.fechaInicio) && Objects.equals(fechaFin, trabajo.fechaFin) && Objects.equals(cliente, trabajo.cliente) && Objects.equals(vehiculo, trabajo.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, fechaFin, horas, cliente, vehiculo);
    }

    @Override
    public String toString() {
        return String.format("Trabajo[fechaInicio=%s, fechaFin=%s, horas=%s, cliente=%s, vehiculo=%s]", this.fechaInicio, this.fechaFin, this.horas, this.cliente, this.vehiculo);
    }
}
