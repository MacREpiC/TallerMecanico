package org.iesalandalus.programacion.tallermecanico;

import javafx.util.Pair;
import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.controlador.IControlador;
import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;

public class Main {
    public static void main(String[] args) {
        Pair<FabricaVista, FabricaFuenteDatos> fabricas = procesarArgumentos(args);
        IControlador controlador = new Controlador(FabricaModelo.CASCADA.crear(fabricas.getValue()), fabricas.getKey().crear());
        controlador.comenzar();
    }
    private static Pair<FabricaVista, FabricaFuenteDatos> procesarArgumentos(String[] args){
        FabricaVista fabricaVista = FabricaVista.TEXTO;
        FabricaFuenteDatos fabricaFuenteDatos = FabricaFuenteDatos.MONGODB;
        for(String argumentos : args) {
            if(argumentos.equalsIgnoreCase("--vventanas")){
                fabricaVista =  FabricaVista.VENTANAS;
            }else if (argumentos.equalsIgnoreCase("-vtexto")) {
                fabricaVista =  FabricaVista.TEXTO;
            }else if (argumentos.equalsIgnoreCase("-fdficheros")) {
                fabricaFuenteDatos = FabricaFuenteDatos.FICHEROS;
            }else if (argumentos.equalsIgnoreCase("-fdmariadb")) {
                fabricaFuenteDatos = FabricaFuenteDatos.MARIADB;
            }else if (argumentos.equalsIgnoreCase("-fdmongodb")){
                fabricaFuenteDatos = FabricaFuenteDatos.MONGODB;
            }
        }
        return new Pair<>(fabricaVista, fabricaFuenteDatos);
    }
}
