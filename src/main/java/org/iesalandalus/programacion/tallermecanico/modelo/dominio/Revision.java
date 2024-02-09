package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5f;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }

    public Revision(Revision revision) {
        Objects.requireNonNull(revision, "La revision no puede ser nula.");
        setCliente(revision.getCliente());
        setVehiculo(revision.getVehiculo());
        setFechaInicio(revision.getFechaInicio());
        setFechaFin(revision.getFechaFin());
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula");
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha final no puede ser nula");
        this.fechaFin = fechaFin;
    }

    public int getHoras(){
        return horas;
    }

    public void anadirHoras(int horas){
        if(horas <= 0){
            throw new IllegalArgumentException("La hora no puede ser menor o igual a cero.");
        }
        this.horas += horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial){
        if(precioMaterial <= 0){
            throw new IllegalArgumentException("El precio material no puede ser menor o igual a cero.");
        }
        this.precioMaterial += precioMaterial;
    }

    public boolean estaCerrada(){
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin){
        if(!fechaFin.isAfter(fechaInicio)){
            throw new IllegalArgumentException("La fecha final tiene que ser posterior a la de inicio");
        }
        this.fechaFin = fechaFin;
    }

    public float getPrecio(){
        long dias = fechaInicio.until(fechaFin).getDays();
        /*
        El método until() devuelve un periodo, representando la diferencia en años,
        meses y días entre dos fechas. Luego, `getDays()`
        obtiene el número de días de ese período.
         */
        return (horas * PRECIO_HORA) + (dias * PRECIO_DIA);
    }

    private float getDias(){
        return (float) horas / 24;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, cliente, vehiculo);
    }

    @Override
    public String toString() {
        return "Revision{" +
                "fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", horas=" + horas +
                ", precioMaterial=" + precioMaterial +
                ", cliente=" + cliente +
                ", vehiculo=" + vehiculo +
                '}';
    }
}