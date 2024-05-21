package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum TipoTrabajo {
    MECANICO ("Mecánico"),
    REVISION ("Revisión");
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
