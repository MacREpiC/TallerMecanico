package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import javax.sound.midi.SysexMessage;
import java.util.List;
import java.util.Objects;


public class Vista {
    private Controlador contolador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "El controlador no puede ser nulo.");
        this.contolador = controlador;
    }

    public void comenzar() {
        Opcion opcion = null;
        while (opcion != Opcion.SALIR) {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        }
    }

    public void terminar() {
        System.out.println("Hasta luego lucas!");
    }

    private void ejecutar(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_CLIENTE -> insertarCliente();
            case BUSCAR_CLIENTE -> buscarCliente();
            case BORRAR_CLIENTE -> borrarCliente();
            case LISTAR_CLIENTES -> listarClientes();
            case MODIFICAR_CLIENTE -> modificarCliente();
            case INSERTAR_VEHICULO -> insertarVehiculo();
            case BUSCAR_VEHICULO -> buscarVehiculo();
            case BORRAR_VEHICULO -> borrarVehiculo();
            case LISTAR_VEHICULOS -> listarVehiculos();
            case INSERTAR_REVISION -> insertarRevision();
            case BUSCAR_REVISION -> buscarRevision();
            case BORRAR_REVISION -> borrarRevision();
            case LISTAR_REVISIONES -> listarRevisiones();
            case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
            case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
            case ANADIR_HORAS_REVISION -> anadirHoras();
            case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
            case CERRAR_REVISION -> cerrar();
            case SALIR -> salir();
            default -> throw new IllegalArgumentException("La opción introducida no es válida.");
        }
    }

    private void insertarCliente() {
        Consola.mostraCabecera("INSERTAR CLIENTE");
        try {
            contolador.insertar(Consola.leerCliente());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarVehiculo() {
        Consola.mostraCabecera("INSERTAR CLIENTE");
        try {
            contolador.insertar(Consola.leerVehiculo());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarRevision() {
        Consola.mostraCabecera("INSERTAR REVISIÓN");
        try {
            contolador.insertar(Consola.leerRevision());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarCliente() {
        Consola.mostraCabecera("BUSCAR CLIENTE");
        try {
            contolador.buscar(Consola.leerCliente());
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarVehiculo() {
        Consola.mostraCabecera("BUSCAR VEHÍCULO");
        try {
            contolador.buscar(Consola.leerRevision());
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarRevision() {
        Consola.mostraCabecera("BUSCAR REVISIÓN");
        try {
            contolador.buscar(Consola.leerRevision());
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void modificarCliente() {
        Consola.mostraCabecera("MODIFICAR CLIENTE");
        boolean esModificado = false;
        try {
            contolador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        if (esModificado) {
            System.out.println("El cliente se ha podido modificar correctamente.");
        }
    }

    private void anadirHoras() {
        Consola.mostraCabecera("AÑADIR HORAS");
        try {
            contolador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void anadirPrecioMaterial() {
        Consola.mostraCabecera("AÑADIR PRECIO MATERIAL");
        try {
            contolador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cerrarRevision() {
        Consola.mostraCabecera("CERRAR REVISIÓN");
        try {
            contolador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarCliente() {
        Consola.mostraCabecera("BORRAR CLIENTE");
        try {
            contolador.borrar(Consola.leerClienteDni());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarClientes() {
        Consola.mostraCabecera("LISTAR CLIENTES");
        List<Cliente> listaClientes = contolador.getClientes();
        if (listaClientes.isEmpty()) {
            System.out.println("La lista de cliente está vacía.");
        } else {
            System.out.println(listaClientes);
        }
    }

    private void listarVehiculos() {
        Consola.mostraCabecera("LISTAR VEHÍCULOS");
        List<Vehiculo> listaVehiculos = contolador.getVehiculos();
        if (listaVehiculos.isEmpty()) {
            System.out.println("La lista de vehículos está vacía.");
        } else {
            System.out.println(listaVehiculos);
        }
    }

    private void listarRevisionesCliente() {
        Consola.mostraCabecera("LISTAR REVISIONES");
        List<Revision> listaRevisiones = contolador.getRevisiones();
        if (listaRevisiones.isEmpty()) {
            System.out.println("La lista de vehículos está vacía.");
        } else {
            System.out.println(listaRevisiones);
        }
    }

    private void listarRevisiones() {
        Consola.mostraCabecera("LISTAR REVISIONES");
        List<Revision> listaRevisiones = contolador.getRevisiones();
        if (listaRevisiones.isEmpty()) {
            System.out.println("La lista de vehículos está vacía.");
        } else {
            System.out.println(listaRevisiones);
        }
    }


}
