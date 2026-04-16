package Interfaces;

import java.util.ArrayList;

import Clases.Eclipse;
import Clases.EventoAstronomico;

public interface EventoRepository {

    /**
     * Agrega un nuevo evento astronómico a la colección.
     */
    void guardar(EventoAstronomico evento);

    /**
     * Devuelve la lista completa de eventos registrados.
     */
    String listarTodos();

    /**
     * Debe buscar el evento más cercano a la fecha/hora actual
     * y devolverlo en formato JSON (usando toJson).
     */
    String proximoEvento();

    /**
     * Filtra y devuelve solo los eclipses que sean visibles
     * en la zona geográfica especificada.
     */
    ArrayList<Eclipse> eclipsesVisiblesEn(String zona);
}