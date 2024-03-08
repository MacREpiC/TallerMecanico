package org.iesalandalus.programacion.tallermecanico.modelo.memoria;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos {
    private final List<Vehiculo> listaVehiculos;

    public Vehiculos() {
        listaVehiculos = new ArrayList<>();
    }

    public List<Vehiculo> get() {
        return new ArrayList<>(listaVehiculos);
    }

    public Vehiculo buscar(Vehiculo vehiculo){
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        int indice = listaVehiculos.indexOf(vehiculo);
        return (indice == -1) ? null : listaVehiculos.get(indice);
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        if(buscar(vehiculo) != null){
            throw new OperationNotSupportedException("Ya existe un vehículo con esa matrícula.");
        }
        listaVehiculos.add(vehiculo);
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehiculo nulo.");
        if(buscar(vehiculo) == null){
            throw new OperationNotSupportedException("No existe ningún vehiculo con esa matrícula.");

        }
        listaVehiculos.remove(vehiculo);
    }
}
