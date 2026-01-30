package DAO;

import Clases.Pasajero;
import Enum.Categoria;

public class DAOpasajeros {
    public static Pasajero[] listaPasajeros(){
        Pasajero[] pasajero = {
            new Pasajero("Alex Milea del Castillo", "X12565780T", "4F", Categoria.ECONOMICA),
            new Pasajero("Rubén Martínez", "08975340Y", "5H", Categoria.EJECUTIVA),
            new Pasajero("Oliver", "Y344567890", "5T", Categoria.PREMIUM)
        };

        return pasajero;
    }
}
