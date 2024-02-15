package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    public void mostraCabecera(String mensaje){
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()));
    }
    public void mostrarMenu(){
        System.out.println("----Opciones----");
        System.out.println(Opcion.opciones);
    }

    private float leerReal(String mensaje){
        System.out.println(mensaje);
        return Entrada.real();
    }

    private int leerEntero(String mensaje){
        System.out.println(mensaje);
        return Entrada.entero();
    }

    private String leerCadena(String mensaje){
        System.out.println(mensaje);
        return Entrada.cadena();
    }

    public LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = null;
        String fechaTexto = null;
        while (fecha == null) {
            while(!fechaTexto.matches(formatoFecha)){
                System.out.println("Por favor, introduce una fecha en el formato dd/MM/yyyy:");
                fechaTexto = Entrada.cadena();
            }
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
}
