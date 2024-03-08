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

    public Trabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }

    public Trabajo(Trabajo trabajo){
        Objects.requireNonNull(trabajo, "No puede ser nulo el trabajo");
        this.cliente = trabajo.cliente;
        this.vehiculo = trabajo.vehiculo;
        this.fechaInicio = trabajo.fechaInicio;
        this.fechaFin = trabajo.fechaFin;
    }

    public Trabajo copiar(Trabajo trabajo){
        if(trabajo instanceof Revision) {
            return new Revision((Revision) trabajo);
        } else if (trabajo instanceof Mecanico) {
            return new Mecanico((Mecanico) trabajo);
        } else {
            throw new IllegalArgumentException("Tipo de trabajo desconocido: " + trabajo.getClass().getSimpleName());
        }
    }

    public Trabajo get(Vehiculo vehiculo) {
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
        if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        this.horas += horas;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        if (estaCerrada()) {
            throw new OperationNotSupportedException("La revisión ya está cerrada.");
        }
        setFechaFin(fechaFin);
    }

    private float getPrecio() {
        float precioFijo = FACTOR_DIA * getDias();
        float precioEspecifico = getPrecioEspecifico();
        return precioFijo + precioEspecifico;
    }

    public abstract float getPrecioEspecifico();

    private float getDias() {
        return (estaCerrada()) ? ChronoUnit.DAYS.between(fechaInicio, fechaFin) : 0;
    }
}
