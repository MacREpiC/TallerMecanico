package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum TipoTrabajo {
    MECANICO ("Mec�nico"),
    REVISION ("Revisi�n");
    String nombre;
    TipoTrabajo(String nombre){
        this.nombre = nombre;
    }
    public static TipoTrabajo get(Trabajo trabajo){
        if(trabajo instanceof Revision){
            return TipoTrabajo.REVISION;
        }else{
            return TipoTrabajo.MECANICO;
        }
    }

    @Override
    public String toString() {
        return nombre;
    }
}
