package org.iesalandalus.programacion.tallermecanico.vista;

public class Consola {
    public void mostraCabecera(String mensaje){
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()));
    }
}
