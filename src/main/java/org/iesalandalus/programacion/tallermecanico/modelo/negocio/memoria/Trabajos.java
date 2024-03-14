package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trabajos implements ITrabajos {
    private final List<Trabajo> coleccionesTrabajos;

    public Trabajos() {
        coleccionesTrabajos = new ArrayList<>();
    }

    @Override
    public List<Trabajo> get() {
        return new ArrayList<>(coleccionesTrabajos);
    }

    @Override
    public List<Trabajo> get(Cliente cliente) {
        List<Trabajo> revisionesCliente = new ArrayList<>();
        for (Trabajo revision : coleccionesTrabajos) {
            if (revision.getCliente().equals(cliente)) {
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;
    }

    @Override
    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> revisionesCliente = new ArrayList<>();
        for (Trabajo revision : coleccionesTrabajos) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;
    }

    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        coleccionesTrabajos.add(trabajo);
    }

    private void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException {
        for(Trabajo revision : coleccionesTrabajos){
            if (!revision.estaCerrado()) {
                if (revision.getCliente().equals(cliente)) {
                    throw new OperationNotSupportedException("El cliente tiene otro trabajo en curso.");
                } else if (revision.getVehiculo().equals(vehiculo)) {
                    throw new OperationNotSupportedException("El vehículo está actualmente en el taller.");
                }
            } else {
                if (revision.getCliente().equals(cliente) && !fechaRevision.isAfter(revision.getFechaFin())) {
                    throw new OperationNotSupportedException("El cliente tiene otro trabajo posterior.");
                } else if (revision.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(revision.getFechaFin())) {
                    throw new OperationNotSupportedException("El vehículo tiene otro trabajo posterior.");
                }
            }
        }
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo añadir horas a un trabajo nulo.");
        getTrabajoAbierto(trabajo.getVehiculo()).anadirHoras(horas);
    }

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) throws OperationNotSupportedException {
        for (Trabajo revision : coleccionesTrabajos) {
            if (revision.getVehiculo().equals(vehiculo) && !revision.estaCerrado()) {
                return revision;
            }
        }
        throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo añadir precio del material a un trabajo nulo.");
        if(trabajo.estaCerrado()){
            throw new OperationNotSupportedException("El trabajo está cerrado");
        }
        if(buscar(trabajo) instanceof Mecanico mecanico){
            mecanico.anadirPrecioMaterial(precioMaterial);
        }else if(buscar(trabajo) instanceof Revision){
            throw new OperationNotSupportedException("No se puede añadir precio al material para este tipo de trabajos.");
        }else if(buscar(trabajo) == null){
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        }
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo cerrar un trabajo nulo.");
        if(trabajo.estaCerrado()){
            throw new OperationNotSupportedException("El trabajo ya está cerrado");
        }else{
            Trabajo revisionAbierta = getTrabajoAbierto(trabajo.getVehiculo());
            revisionAbierta.cerrar(fechaFin);
        }
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar un trabajo nulo.");
        if (!coleccionesTrabajos.contains(trabajo)) {
            trabajo = null;
        }
        return trabajo;
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede borrar un trabajo nulo.");
        if(buscar(trabajo) == null){
            throw new OperationNotSupportedException("No existe ningún trabajo igual.");
        }
        coleccionesTrabajos.remove(trabajo);
    }
}