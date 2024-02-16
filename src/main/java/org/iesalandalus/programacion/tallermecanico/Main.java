package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = null;
        String fechaTexto = null;

        while (fecha == null) {
            System.out.print("Por favor, introduce una fecha en el formato dd/MM/yyyy: ");
            fechaTexto = Entrada.cadena();
            try {
                fecha = LocalDate.parse(fechaTexto, formatoFecha);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha no válida. Por favor, vuelve a intentarlo.");
            }
        }
        System.out.println("Fecha introducida correctamente: " + fecha.format(formatoFecha));
    }
}