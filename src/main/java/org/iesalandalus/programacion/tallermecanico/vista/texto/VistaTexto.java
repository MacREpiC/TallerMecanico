package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class VistaTexto{
    public static final String DNI_EJEMPLO = "12345678D";
    public static final String MATRICULA_DEFECTO = "1234BCD";
    private GestorEventos gestorEventos = new GestorEventos(Evento.values());

    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    public void comenzar() throws OperationNotSupportedException {
        Evento evento;
        do {
            Consola.mostrarMenu();
            evento = Consola.elegirOpcion();
            ejecutar(evento);
        } while (evento != Evento.SALIR);
        terminar();
    }

    private void ejecutar(Evento opcion) throws OperationNotSupportedException {
        getGestorEventos().notificar(opcion);
    }

    public void terminar() {
        System.out.printf("¡HASTA PRONTO!%n");
    }

    public Cliente leerCliente() {
        Cliente cliente = null;
        boolean clienteCorrecto = false;
        do {
            try {
                cliente = new Cliente(Consola.leerCadena("Dime el nombre del cliente: "), Consola.leerCadena("Dime el dni del cliente: "), Consola.leerCadena("Dime el teléfono del cliente: "));
                clienteCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.printf(e.getMessage());
            }
        } while (!clienteCorrecto);
        return cliente;
    }

    public Cliente leerClienteDni() {
        Cliente cliente = null;
        boolean clienteCorrecto = false;
        do {
            try {
                cliente = new Cliente(Cliente.get(Consola.leerCadena("Dime el DNI del cliente: ")));
                clienteCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.printf(e.getMessage());
            }
        } while (!clienteCorrecto);
        return cliente;
    }

    public String leerNuevoNombre() {
        String nombre;
        boolean nombreCorrecto = false;
        do {
            nombre = Consola.leerCadena("Dime el nuevo nombre del cliente: ");
            if (!nombre.isBlank()) {
                try {
                    new Cliente(nombre, VistaTexto.DNI_EJEMPLO, "600600600");
                    nombreCorrecto = true;
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.printf(e.getMessage());
                }
            } else {
                nombreCorrecto = true;
            }
        } while (!nombreCorrecto);
        return nombre;
    }

    public String leerNuevoTelefono() {
        String telefono;
        boolean telefonoCorrecto = false;
        do {
            telefono = Consola.leerCadena("Dime el nuevo teléfono del cliente: ");
            if (!telefono.isBlank()) {
                try {
                    new Cliente("Juan", VistaTexto.DNI_EJEMPLO, telefono);
                    telefonoCorrecto = true;
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.printf(e.getMessage());
                }
            } else {
                telefonoCorrecto = true;
            }

        } while (!telefonoCorrecto);
        return telefono;
    }

    public Vehiculo leerVehiculo() {
        Vehiculo vehiculo = null;
        boolean vehiculoCorrecto = false;

        do {
            try {
                vehiculo = new Vehiculo(Consola.leerCadena("Dime la marca del vehículo: "), Consola.leerCadena("Dime el modelo del vehículo: "), Consola.leerCadena("Dime la matrícula del vehículo: "));
                vehiculoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.printf(e.getMessage());
            }
        } while (!vehiculoCorrecto);
        return vehiculo;
    }

    public Vehiculo leerVehiculoMatricula() {
        Vehiculo vehiculo = null;
        boolean vehiculoCorrecto = false;
        do {
            try {
                vehiculo = Vehiculo.get(Consola.leerCadena("Dime la matrícula del vehículo: "));
                vehiculoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.printf(e.getMessage());
            }
        } while (!vehiculoCorrecto);
        return vehiculo;
    }

    public Trabajo leerRevision() {
        Revision revision = null;
        boolean trabajoCorrecto = false;

        do {
            try {
                revision = new Revision(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Dime la fecha de inicio del trabajo: "));
                trabajoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.printf(e.getMessage());
            }
        } while (!trabajoCorrecto);
        return revision;
    }

    public Trabajo leerMecanico() {
        Trabajo mecanico = null;
        boolean trabajoCorrecto = false;

        do {
            try {
                mecanico = new Mecanico(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Dime la fecha de inicio del trabajo: "));
                trabajoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.printf(e.getMessage());
            }
        } while (!trabajoCorrecto);
        return mecanico;
    }

    public Trabajo leerTrabajoVehiculo() {
        return Trabajo.get(leerVehiculo());
    }

    public int leerHoras() {
        int horas;
        boolean horasCorrectas = false;
        do {
            horas = Consola.leerEntero("Dime las horas que quieres añadir: ");
            try {
                Revision revision = new Revision(Cliente.get(VistaTexto.DNI_EJEMPLO), Vehiculo.get(VistaTexto.MATRICULA_DEFECTO), LocalDate.now());
                revision.anadirHoras(horas);
                horasCorrectas = true;
            } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
                System.out.printf(e.getMessage());
            }
        } while (!horasCorrectas);
        return horas;
    }

    public float leerPrecioMaterial() {
        float precioMaterial;
        boolean precioCorrecto = false;
        do {
            precioMaterial = Consola.leerReal("Dime el precio que quieres añadir: ");
            try {
                Mecanico mecanico = new Mecanico(Cliente.get(VistaTexto.DNI_EJEMPLO), Vehiculo.get(VistaTexto.MATRICULA_DEFECTO), LocalDate.now());
                mecanico.anadirPrecioMaterial(precioMaterial);
                precioCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
                System.out.printf(e.getMessage());
            }
        } while (!precioCorrecto);
        return precioMaterial;
    }

    public LocalDate leerFechaCierre() {
        LocalDate fechaCierre;
        boolean fechaCierreCorrecta = false;
        do {
            fechaCierre = Consola.leerFecha("Dime la fecha de cierre: ");
            try {
                Revision revision = new Revision(Cliente.get(VistaTexto.DNI_EJEMPLO), Vehiculo.get(VistaTexto.MATRICULA_DEFECTO), LocalDate.of(1900, 1, 1));
                revision.cerrar(fechaCierre);
                fechaCierreCorrecta = true;
            } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
                System.out.printf(e.getMessage());
            }
        } while (!fechaCierreCorrecta);
        return fechaCierre;
    }

    public void mostrarCliente(Cliente cliente){

    }

    public void notificarResultado(Evento evento, String texto, boolean exito){
        Consola.mostraCabecera(evento.toString());
        if(exito){
            System.out.printf(texto);
        }else{
            System.out.printf("ERROR: %s%n", texto);
        }
    }
}