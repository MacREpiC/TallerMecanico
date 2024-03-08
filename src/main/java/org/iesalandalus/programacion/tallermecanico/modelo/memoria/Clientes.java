package org.iesalandalus.programacion.tallermecanico.modelo.memoria;

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
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int indice = listaClientes.indexOf(cliente);
        return (indice == -1) ? null : listaClientes.get(indice);
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if(buscar(cliente) != null){
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        listaClientes.add(cliente);
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if(buscar(cliente) == null){
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");

        }
        listaClientes.remove(cliente);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        Cliente clienteBuscar;
        boolean modificado = false;
        if (buscar(cliente) == null) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        } else {
            clienteBuscar = buscar(cliente);
        }
        if (nombre != null && telefono != null) {
            clienteBuscar.setNombre(nombre);
            clienteBuscar.setTelefono(telefono);
            modificado = true;
        }
        if (telefono == null && nombre != null && !nombre.isBlank()) {
            clienteBuscar.setNombre(nombre);
            modificado = true;
        } else if (nombre == null && telefono != null && !telefono.isBlank()) {
            clienteBuscar.setTelefono(telefono);
            modificado = true;
        }
        return modificado;
    }
}
