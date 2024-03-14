package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import javax.naming.OperationNotSupportedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModeloCascada implements Modelo {
    private IClientes clientes;
    private IVehiculos vehiculos;
    private ITrabajos trabajos;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos){
        IFuenteDatos fuenteDatos = fabricaFuenteDatos.crear();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        trabajos = fuenteDatos.crearTrabajos();
    }

    @Override
    public void comenzar(){
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        trabajos = new Trabajos();
    }

    @Override
    public void terminar() {
        System.out.println("El modelo ha finalizado.");
    }

    @Override
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        if(trabajo instanceof Revision revision){
            trabajos.insertar(revision);
        } else if(trabajo instanceof Mecanico mecanico){
            trabajos.insertar(mecanico);
        }
    }

    @Override
    public Cliente buscar(Cliente cliente){
        return new Cliente(Objects.requireNonNull(clientes.buscar(cliente), "No existe un cliente igual."));
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo){
        return Objects.requireNonNull(vehiculos.buscar(vehiculo), "No existe un vehículo igual.");
    }

    @Override
    public Trabajo buscar(Trabajo trabajo){
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        trabajo = trabajos.buscar(trabajo);
        if(trabajo instanceof Mecanico mecanico){
            trabajo = new Mecanico(mecanico);
        }else if(trabajo instanceof Revision revision){
            trabajo = new Revision(revision);
        }
        return trabajo;
    }

    @Override
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente, nombre, telefono);
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        trabajos.anadirHoras(trabajo, horas);
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        trabajos.anadirPrecioMaterial(trabajo, precioMaterial);
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        trabajos.cerrar(trabajo, fechaFin);
    }

    @Override
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        for(Trabajo trabajo : trabajos.get(cliente)){
            trabajos.borrar(trabajo);
        }
        clientes.borrar(cliente);
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        for(Trabajo trabajo : trabajos.get(vehiculo)){
            trabajos.borrar(trabajo);
        }
        vehiculos.borrar(vehiculo);
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        trabajos.borrar(trabajo);
    }

    @Override
    public List<Cliente> getClientes() {
        List<Cliente> listaExistente = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            listaExistente.add(new Cliente(cliente));
        }
        return listaExistente;
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }

    @Override
    public List<Trabajo> getTrabajos() {
        List<Trabajo> trabajosExistentes = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get()) {
            if(trabajo instanceof Mecanico mecanico){
                trabajosExistentes.add(new Mecanico(mecanico));
            } else if (trabajo instanceof Revision revision) {
                trabajosExistentes.add(new Revision(revision));
            }
        }
        return trabajosExistentes;
    }
}
