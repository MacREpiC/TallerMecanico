package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {
    INSERTAR_CLIENTE(1,". Insertar cliente."),
    BUSCAR_CLIENTE(2,". Buscar cliente."),
    BORRAR_CLIENTE(3,". Borrar cliente."),
    LISTAR_CLIENTES(4,". Listar clientes."),
    MODIFICAR_CLIENTE(5,". Modificar cliente."),
    INSERTAR_VEHICULO(6,". Insertar vehiculo"),
    BUSCAR_VEHICULO(7,". Buscar vehículo."),
    BORRAR_VEHICULO(8,". Borrar vehículo."),
    LISTAR_VEHICULOS(9,". Listar vehículos."),
    INSERTAR_REVISION(10,". Insertar revisión."),
    BUSCAR_REVISION(11,". Buscar revisión."),
    BORRAR_REVISION(12,". Borrar revisión."),
    LISTAR_REVISIONES(13,". Listar revisiones."),
    LISTAR_REVISIONES_CLIENTE(14,". Listar revisiones cliente."),
    LISTAR_REVISIONES_VEHICULO(15,". Listar revisiones vehículo."),
    ANADIR_HORAS_REVISION(16, ". Añadir horas a la revisión."),
    ANADIR_PRECIO_MATERIAL_REVISION(17, ". Añadir precio material a la revision."),
    CERRAR_REVISION(18,". Cerra revisión."),
    SALIR(19,". Salir.");

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
