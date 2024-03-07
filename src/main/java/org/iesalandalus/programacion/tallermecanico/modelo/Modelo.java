package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import javax.naming.OperationNotSupportedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Modelo {
    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    public void comenzar(){
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
    }

    public void terminar() {
        System.out.println("El modelo ha finalizado.");
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws OperationNotSupportedException {
        Cliente clienteEncontrado = clientes.buscar(revision.getCliente());
        Vehiculo vehiculoEncontrado = vehiculos.buscar(revision.getVehiculo());
        revision = new Revision(clienteEncontrado, vehiculoEncontrado, revision.getFechaInicio());
        revisiones.insertar(revision);
    }

    public Cliente buscar(Cliente cliente){
        return new Cliente(Objects.requireNonNull(clientes.buscar(cliente), "No existe un cliente igual."));
    }

    public Vehiculo buscar(Vehiculo vehiculo){
        return Objects.requireNonNull(vehiculos.buscar(vehiculo), "No existe un vehículo igual.");
    }

    public Revision buscar(Revision revision){
        return new Revision(Objects.requireNonNull(revisiones.buscar(revision), "No existe una revisión igual."));
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        revisiones.anadirHoras(revision, horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        revisiones.cerrar(revision, fechaFin);
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        for(Revision revision : revisiones.get(vehiculo)){
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        for(Revision revision : revisiones.get(cliente)){
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
        revisiones.borrar(revision);
    }

    public List<Cliente> getClientes() {
        List<Cliente> listaExistente = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            listaExistente.add(new Cliente(cliente));
        }
        return listaExistente;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }

    public List<Revision> getRevisiones() {
        List<Revision> revisionesExistentes = new ArrayList<>();
        for (Revision revision : revisiones.get()) {
            revisionesExistentes.add(new Revision(revision));
        }
        return revisionesExistentes;
    }

    public List<Revision> getRevisiones(Cliente cliente) {
        List<Revision> revisionesVehiculosExistentes = new ArrayList<>();
        for (Revision revision : revisiones.get(cliente)) {
            revisionesVehiculosExistentes.add(new Revision(revision));
        }
        return revisionesVehiculosExistentes;
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculosExistentes = new ArrayList<>();
        for (Revision revision : revisiones.get(vehiculo)) {
            revisionesVehiculosExistentes.add(new Revision(revision));
        }
        return revisionesVehiculosExistentes;
    }
}
