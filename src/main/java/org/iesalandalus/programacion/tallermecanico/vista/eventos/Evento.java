package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {
    INSERTAR_CLIENTE(1, "Insertar cliente"),
    BUSCAR_CLIENTE(2, "Buscar cliente"),
    BORRAR_CLIENTE(3,"Borrar cliente"),
    LISTAR_CLIENTES(4, "Listar clientes"),
    MODIFICAR_CLIENTE(5, "Modificar cliente"),
    INSERTAR_VEHICULO(6, "Insertar vehiculo"),
    BUSCAR_VEHICULO(7, "Buscar vehiculo"),
    BORRAR_VEHICULO(8, "Borrar vehiculo"),
    LISTAR_VEHICULOS(9, "Listar vehículos"),
    INSERTAR_REVISION(10, "Insertar revisión"),
    INSERTAR_MECANICO(11, "Insertar mecanico"),
    BUSCAR_TRABAJO(12, "Buscar trabajo"),
    BORRAR_TRABAJO(13, "Borrar trabajo"),
    LISTAR_TRABAJOS(14, "Listar trabajos"),
    LISTAR_TRABAJOS_CLIENTE(15, "Listar trabajos del cliente"),
    LISTAR_TRABAJOS_VEHICULO(16, "Listar trabajos del vehiculo"),
    ANADIR_HORAS_TRABAJO(17, "Añadir horas trabajo"),
    ANADIR_PRECIO_MATERIAL_TRABAJO(18, "Añadir precio de material a un trabjo."),
    CERRAR_TRABAJO(19, "Cerrar trabajo"),
    SALIR(19, "Salir");

    private final int codigo;
    private final String texto;
    static final Map<Integer, Evento> eventos;

    static{
        eventos = new HashMap<>();
        for (Evento opcion : values()) {
            eventos.put(opcion.codigo,opcion);
        }
    }
    Evento(int codigo, String texto){
        this.texto = texto;
        this.codigo = codigo;
    }
    public static boolean esValido(int numeroOpcion){
        return eventos.containsKey(numeroOpcion);
    }
    public static Evento get(int codigo){
        if (!esValido(codigo)){
            throw new IllegalArgumentException("Opción no válida.");
        }
        return eventos.get(codigo);
    }

    @Override
    public String toString() {
        return String.format("%d-%s", this.codigo, this.texto);
    }
}
