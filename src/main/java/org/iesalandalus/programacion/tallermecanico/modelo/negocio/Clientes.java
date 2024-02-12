package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {
    private final List<Cliente> listaClientes;

    public Clientes() {
        listaClientes = new ArrayList<>();
    }

    public List<Cliente> get() {
        return new ArrayList<>(listaClientes);
    }

    public Cliente buscar(Cliente cliente){
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo");
        if (!listaClientes.contains(cliente)){
            throw new IllegalArgumentException("No se ha encontrado el cliente");
        }
        return cliente;
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo");
        if(buscar(cliente) != null){
            throw new OperationNotSupportedException("El cliente pasado por parámetro ya existe.");
        }
        listaClientes.add(cliente);
    }


    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo");
        if(buscar(cliente) == null){
            throw new OperationNotSupportedException("No existe el cliente.");

        }
        listaClientes.remove(cliente);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        Cliente clienteBuscar;
        boolean modificado = false;
        if (buscar(cliente) == null) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        } else {
            clienteBuscar = buscar(cliente);
        }
        if(nombre != null && nombre.isBlank()){
            clienteBuscar.setNombre(nombre);
            modificado = true;
        }
        if(telefono != null && !telefono.isBlank()){
            clienteBuscar.setTelefono(telefono);
            modificado = true;
        }
        return modificado;
    }
}
