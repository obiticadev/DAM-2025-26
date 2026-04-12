package bloque3;

/**
 * Clase Celda para el Ejercicio 18 — Composicion.
 * Cada celda tiene un estado (libre/ocupado) y un id de reserva.
 */
public class Ej18_Celda {
    private static int contadorGlobal = 0;

    private char estado;
    private int idReserva;

    // TODO 1: Implementa el constructor. Estado inicial = 'L', idReserva = 0.
    public Ej18_Celda() {
        throw new UnsupportedOperationException("Implementa el constructor");
    }

    // TODO 2: Implementa ocupar() — cambia estado a 'O', asigna id = ++contadorGlobal.
    public void ocupar() { }

    // TODO 3: Implementa liberar() — cambia estado a 'L', idReserva = 0.
    public void liberar() { }

    public char getEstado() { return estado; }
    public int getIdReserva() { return idReserva; }
    public static int getContadorGlobal() { return contadorGlobal; }
    public static void resetContador() { contadorGlobal = 0; }
}
