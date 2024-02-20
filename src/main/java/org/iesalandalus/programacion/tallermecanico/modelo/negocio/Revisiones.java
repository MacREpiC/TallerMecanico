package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {
    private final List<Revision> listaRevisiones;

    public Revisiones() {
        listaRevisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(listaRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        List<Revision> revisionesCliente = new ArrayList<>();
        for (Revision revision : listaRevisiones) {
            if (revision.getCliente().equals(cliente)) {
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;
    }
    public List<Revision> get(Vehiculo vehiculo) {
        List<Revision> revisionesCliente = new ArrayList<>();
        for (Revision revision : listaRevisiones) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision, "No se puede buscar una revisión nula.");
        if (!listaRevisiones.contains(revision)) {
            revision = null;
        }
        return revision;
    }

    public void insertar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No se puede insertar una revisión nula.");
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        listaRevisiones.add(revision);
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException {
        for(Revision revision : listaRevisiones){
            if (!revision.estaCerrada()) {
                if (revision.getCliente().equals(cliente)) {
                    throw new OperationNotSupportedException("El cliente tiene otra revisión en curso.");
                } else if (revision.getVehiculo().equals(vehiculo)) {
                    throw new OperationNotSupportedException("El vehículo está actualmente en revisión.");
                }
            } else {
                if (revision.getCliente().equals(cliente) && !fechaRevision.isAfter(revision.getFechaFin())) {
                    throw new OperationNotSupportedException("El cliente tiene una revisión posterior.");
                } else if (revision.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(revision.getFechaFin())) {
                    throw new OperationNotSupportedException("El vehículo tiene una revisión posterior.");
                }
            }
        }
    }
    private Revision getRevision(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        if (buscar(revision) == null) {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        return revision;
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        getRevision(revision).anadirHoras(horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        getRevision(revision).anadirPrecioMaterial(precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        getRevision(revision).cerrar(fechaFin);
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No se puede borrar una revisión nula.");
        if(buscar(revision) == null){
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        listaRevisiones.remove(revision);
    }
}
