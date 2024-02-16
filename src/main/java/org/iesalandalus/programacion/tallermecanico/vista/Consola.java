package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "\\d{2}/\\d{2}/\\d{4}";
    public void mostraCabecera(String mensaje){
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()));
    }
    public void mostrarMenu(){
        System.out.println("----Opciones----");
        System.out.println(Opcion.opciones);
    }

    private static float leerReal(String mensaje){
        System.out.println(mensaje);
        return Entrada.real();
    }

    private static int leerEntero(String mensaje){
        System.out.println(mensaje);
        return Entrada.entero();
    }

    private static String leerCadena(String mensaje){
        System.out.println(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = null;
        String fechaTexto = null;
        while (fecha == null) {
            System.out.println(mensaje);
            System.out.println("Por favor, introduce una fecha en el formato dd/MM/yyyy:");
            fechaTexto = Entrada.cadena();
            try {
                fecha = LocalDate.parse(fechaTexto, formatoFecha);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha no válida. Por favor, vuelve a intentarlo.");
            }
        }
        return fecha;
    }

    public Opcion elegirOpcion(){
        int numeroOpcion;
        while (true) {
            System.out.print("Introduce el número de la opción: ");
            numeroOpcion = Entrada.entero();
            if (Opcion.esValida(numeroOpcion)) {
                return Opcion.opciones.get(numeroOpcion);
            } else {
                System.out.println("Opción no válida. Por favor, vuelve a intentarlo.");
            }
        }
    }

    public  Cliente leerCliente(){
        String nombre = Consola.leerCadena("Introduce un nombre válido: ");
        String dni = Consola.leerCadena("Introduce un DNI válido: ");
        String telefono = Consola.leerCadena("Introduce un teléfono válido: ");
        return new Cliente(nombre, dni, telefono);
    }

    public Cliente leerClienteDni(){
        String dni = Consola.leerCadena("Introduce el DNI: ");
        return Cliente.get(dni);
    }

    public String leerNuevoNombre(){
        return leerCadena("Introduce el nuevo nombre del cliente:");
    }

    public String leerNuevoTelefono(){
        return leerCadena("Introduce el nuevo teléfono del cliente:");
    }

    public Vehiculo leerVehiculo(){
        String marca = leerCadena("Introduce una marca válida: ");
        String modelo = leerCadena("Introduce un modelo válido: ");
        String matricula = leerCadena("Introduce una matrícula válida: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    public Vehiculo leerVehiculoMatricula(){
        String matricula = Consola.leerCadena("Introduce la matrícula: ");
        return Vehiculo.get(matricula);
    }

    public Revision leerRevision(){
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio: ");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    public int leerHoras(){
        return leerEntero("Introduce el número de horas correspondientes: ");
    }

    public float leerPrecioMaterial(){
        System.out.print("Introduce su correspondiente precio material: ");
        return Entrada.real();
    }

    public LocalDate leerFechaCierre(){
        return leerFecha("Introduce la fecha de cierre de la revisión: ");
    }
}
