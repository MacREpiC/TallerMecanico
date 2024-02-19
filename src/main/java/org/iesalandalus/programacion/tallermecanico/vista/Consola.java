package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";
    public static void mostraCabecera(String mensaje){
        System.out.println(/*ESPACIO*/);
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()));
    }
    public static void mostrarMenu(){
        mostraCabecera("OPCIONES");
        for (int i = 0; i < Opcion.values().length; i++) {
            System.out.println(Opcion.values()[i]);
        }
    }

    private static float leerReal(String mensaje){
        System.out.print(mensaje);
        return Entrada.real();
    }

    private static int leerEntero(String mensaje){
        System.out.print(mensaje);
        return Entrada.entero();
    }

    private static String leerCadena(String mensaje){
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje) {
        Pattern patron = Pattern.compile(CADENA_FORMATO_FECHA);
        DateTimeFormatter comparador = DateTimeFormatter.ofPattern(String.valueOf(patron));
        LocalDate fecha = null;
        try {
            String cadenaFecha = leerCadena(mensaje);
            fecha = LocalDate.parse(cadenaFecha, comparador);
        } catch (DateTimeParseException e) {
            System.out.println("La fecha introducida no es válida, inténtelo de nuevo.");
        }
        return fecha;
    }

    public static Opcion elegirOpcion(){
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

    public static Cliente leerCliente(){
        String nombre = Consola.leerCadena("Introduce un nombre válido: ");
        String dni = Consola.leerCadena("Introduce un DNI válido: ");
        String telefono = Consola.leerCadena("Introduce un teléfono válido: ");
        return new Cliente(nombre, dni, telefono);
    }

    public static Cliente leerClienteDni(){
        String dni = Consola.leerCadena("Introduce el DNI: ");
        return Cliente.get(dni);
    }

    public static String leerNuevoNombre(){
        return leerCadena("Introduce el nuevo nombre del cliente:");
    }

    public static String leerNuevoTelefono(){
        return leerCadena("Introduce el nuevo teléfono del cliente:");
    }

    public static Vehiculo leerVehiculo(){
        String marca = leerCadena("Introduce una marca válida: ");
        String modelo = leerCadena("Introduce un modelo válido: ");
        String matricula = leerCadena("Introduce una matrícula válida: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerVehiculoMatricula(){
        String matricula = Consola.leerCadena("Introduce la matrícula: ");
        return Vehiculo.get(matricula);
    }

    public static Revision leerRevision(){
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio: ");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    public static int leerHoras(){
        return leerEntero("Introduce el número de horas correspondientes: ");
    }

    public static float leerPrecioMaterial(){
        return leerReal("Introduce su correspondiente precio material: ");
    }

    public static LocalDate leerFechaCierre(){
        return leerFecha("Introduce la fecha de cierre de la revisión: ");
    }
}
