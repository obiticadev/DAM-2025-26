package bloque4;

import java.util.ArrayList;

/**
 * EJERCICIO 24 — Ejercicio completo tipo examen (sin Scanner, testeable)
 * Teoria: teoria/04_DAO_Menu.md (seccion 4)
 *
 * Sistema de gestion de aulas con asientos para un centro de estudios.
 * Integra: Clase con array bidi, DAO con ArrayList, logica de negocio.
 */
public class Ej24_Completo {

    // --- Clase interna Aula ---
    public static class Aula {
        private int numAula;
        private int[][] pupitres; // 0 = libre, >0 = ID alumno
        private static int contadorAlumnos = 0;

        // TODO 1: Implementa constructor(int numAula, int filas, int cols).
        //         Valida dimensiones > 0. Inicializa pupitres a 0.
        public Aula(int numAula, int filas, int cols) {
            throw new UnsupportedOperationException("Implementa constructor Aula");
        }

        // TODO 2: Implementa asignar(int fila, int col) — si libre, asigna ++contadorAlumnos.
        //         Devuelve el id asignado o -1 si falla.
        public int asignar(int fila, int col) { return -1; }

        // TODO 3: Implementa desasignar(int idAlumno) — busca en todo el array,
        //         si encuentra el id lo pone a 0. Devuelve boolean.
        public boolean desasignar(int idAlumno) { return false; }

        public int plazasLibres() {
            int c = 0;
            for (int[] fila : pupitres) for (int v : fila) if (v == 0) c++;
            return c;
        }

        public int plazasOcupadas() {
            int c = 0;
            for (int[] fila : pupitres) for (int v : fila) if (v > 0) c++;
            return c;
        }

        public int getNumAula() { return numAula; }
        public int getFilas() { return pupitres.length; }
        public int getColumnas() { return pupitres[0].length; }
        public static void resetContador() { contadorAlumnos = 0; }

        public String mostrar() {
            StringBuilder sb = new StringBuilder();
            for (int[] fila : pupitres) {
                for (int j = 0; j < fila.length; j++) {
                    sb.append(String.format("%3d", fila[j]));
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    // --- DAO de Aulas ---
    private ArrayList<Aula> aulas;

    // TODO 4: Implementa constructor. Inicializa lista y llama a cargarDatos().
    public Ej24_Completo() {
        throw new UnsupportedOperationException("Implementa constructor DAO");
    }

    // TODO 5: Implementa cargarDatos() — agrega al menos 3 aulas con distintas dimensiones.
    private void cargarDatos() { }

    // TODO 6: Implementa buscarAula(int numAula) — devuelve Aula o null.
    public Aula buscarAula(int numAula) { return null; }

    // TODO 7: Implementa aulaConMasPlazasLibres() — devuelve el Aula con mas plazas libres.
    //         Si hay empate, la primera. Si lista vacia, null.
    public Aula aulaConMasPlazasLibres() { return null; }

    public ArrayList<Aula> listar() { return new ArrayList<>(aulas); }

    public int totalPlazasLibres() {
        int total = 0;
        for (Aula a : aulas) total += a.plazasLibres();
        return total;
    }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 24: Completo ===\n");
        Aula.resetContador();
        Ej24_Completo centro = new Ej24_Completo();
        for (Aula a : centro.listar()) {
            System.out.println("Aula " + a.getNumAula() + ": " + a.plazasLibres() + " libres");
        }
    }
}
