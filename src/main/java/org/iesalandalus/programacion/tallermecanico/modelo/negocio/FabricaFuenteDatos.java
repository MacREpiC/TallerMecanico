package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.FuenteDatosFichero;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mariadb.FuenteDatosMariaDB;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongodb.FuenteDatosMongoDB;

public enum FabricaFuenteDatos {

    FICHEROS {
        @Override
        public IFuenteDatos crear() {
            return new FuenteDatosFichero();
        }
    },
    MARIADB{
        @Override
        public IFuenteDatos crear(){
            return new FuenteDatosMariaDB();
        }
    },
    MONGODB{
        @Override
        public IFuenteDatos crear(){return new FuenteDatosMongoDB();}
    };
    public abstract IFuenteDatos crear();
}