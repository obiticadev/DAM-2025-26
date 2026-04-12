package bloque4;

import java.util.ArrayList;

/**
 * EJERCICIO 23 — Integracion Clase + DAO
 * Teoria: teoria/04_DAO_Menu.md (seccion 4)
 *
 * Clase Cine que integra una Sala (con array bidi de chars) y un DAO de sesiones.
 * Sin Scanner — todo testeable por JUnit.
 */
public class Ej23_Integracion {

    // --- Clase interna Sesion ---
    public static class Sesion {
        private int numSesion;
        private String pelicula;
        private char[][] asientos; // 'L' libre, 'O' ocupado
        private double precioEntrada;

        // TODO 1: Implementa constructor(int num, String peli, int filas, int cols, double precio).
        //         Inicializa todos los asientos a 'L'.
        public Sesion(int numSesion, String pelicula, int filas, int cols, double precioEntrada) {
            throw new UnsupportedOperationException("Implementa el constructor");
        }

        // TODO 2: Implementa reservar(int fila, int col) — valida rango, comprueba 'L',
        //         cambia a 'O'. Devuelve boolean.
        public boolean reservar(int fila, int col) { return false; }

        // TODO 3: Implementa liberar(int fila, int col) — valida rango, comprueba 'O',
        //         cambia a 'L'. Devuelve boolean.
        public boolean liberar(int fila, int col) { return false; }

        public int asientosLibres() {
            int count = 0;
            for (char[] fila : asientos) for (char c : fila) if (c == 'L') count++;
            return count;
        }

        public double recaudacion() {
            int ocupados = 0;
            for (char[] fila : asientos) for (char c : fila) if (c == 'O') ocupados++;
            return ocupados * precioEntrada;
        }

        public String mostrar() {
            StringBuilder sb = new StringBuilder();
            for (char[] fila : asientos) {
                for (int j = 0; j < fila.length; j++) {
                    sb.append(fila[j]);
                    if (j < fila.length - 1) sb.append(" ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        public int getNumSesion() { return numSesion; }
        public String getPelicula() { return pelicula; }
        public int getFilas() { return asientos.length; }
        public int getColumnas() { return asientos[0].length; }
    }

    // --- DAO de Sesiones ---
    private ArrayList<Sesion> sesiones;

    // TODO 4: Implementa constructor. Inicializa lista y carga datos con cargarDatos().
    public Ej23_Integracion() {
        throw new UnsupportedOperationException("Implementa el constructor");
    }

    // TODO 5: Implementa cargarDatos() — agrega al menos 3 sesiones con datos distintos.
    private void cargarDatos() { }

    // TODO 6: Implementa buscarSesion(int numSesion) — devuelve la Sesion o null.
    public Sesion buscarSesion(int numSesion) { return null; }

    // TODO 7: Implementa recaudacionTotal() — suma recaudacion() de todas las sesiones.
    public double recaudacionTotal() { return 0.0; }

    public ArrayList<Sesion> listarSesiones() { return new ArrayList<>(sesiones); }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 23: Integracion ===\n");
        Ej23_Integracion cine = new Ej23_Integracion();
        for (Sesion s : cine.listarSesiones()) {
            System.out.println("Sesion " + s.getNumSesion() + ": " + s.getPelicula());
        }
    }
}
